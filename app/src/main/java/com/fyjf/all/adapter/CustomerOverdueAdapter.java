package com.fyjf.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fyjf.all.R;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.dao.utils.TimeUtil;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class CustomerOverdueAdapter extends BaseRecyclerAdapter<CustomerOverdueAdapter.SimpleAdapterViewHolder> {

    private List<OverdueReport> list;
    private Context mContext;

    public CustomerOverdueAdapter(Context context, List<OverdueReport> list) {
        this.list = list;
        this.mContext = context;
    }
    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_overdue_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        OverdueReport overdueReport = list.get(position);
        holder.overdue_name.setText(overdueReport.getCustomerName());
        holder.overdue_msg.setText(overdueReport.getMsgCount()+"");
        holder.overdue_time.setText("逾期"+overdueReport.getOverdueDays()+"天 | ");
        holder.overdue_price.setText("¥"+overdueReport.getOverdueMoney()+"万");
        holder.overdue_manger.setText(overdueReport.getManagerName());
        holder.overdue_date.setText(TimeUtil.timeHao2Date(overdueReport.getOverdueStart(),"yyyy-MM-dd"));

        String sourceStr = overdueReport.getOverdueImgs();
        if(TextUtils.isEmpty(overdueReport.getOverdueImgs())){
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.overdue_img_1);
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.overdue_img_2);
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.overdue_img_3);
        }else{
            String[] sourceStrArray = sourceStr.split(",");
            int imgs = 1;
            for (int i = 0; i < sourceStrArray.length; i++) {
                if(!TextUtils.isEmpty(sourceStrArray[i])){
                    if(imgs>3){
                        break;
                    }
                    if(imgs==1){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.overdue_img_1);
                    }else if(imgs==2){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.overdue_img_2);
                    }else if(imgs==3){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.overdue_img_3);
                    }
                    imgs++;
                }
            }
        }

        onClick(holder, position);

    }

    private void onClick(SimpleAdapterViewHolder holder, final int position) {
        holder.overdue_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openMsg(position);
            }
        });
        holder.overdue_img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openProgress(position);
            }
        });
        holder.overdue_img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openProgress(position);
            }
        });
        holder.overdue_img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openProgress(position);
            }
        });
        holder.overdue_quantified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openProgress(position);
            }
        });
        holder.overdue_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openReport(position);
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return list!=null?list.size():0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout customer_item;
        public TextView overdue_state;
        public TextView overdue_name;
        public TextView overdue_msg;
        public ImageView overdue_img_1;
        public ImageView overdue_img_2;
        public ImageView overdue_img_3;
        public TextView overdue_time;
        public TextView overdue_price;
        public TextView overdue_manger;
        public TextView overdue_date;
        public TextView overdue_report;
        public TextView overdue_quantified;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                customer_item = (RelativeLayout) itemView.findViewById(R.id.customer_item);
                overdue_state = (TextView) itemView.findViewById(R.id.overdue_state);
                overdue_name = (TextView) itemView.findViewById(R.id.overdue_name);
                overdue_msg = (TextView) itemView.findViewById(R.id.overdue_msg);
                overdue_img_1 = (ImageView) itemView.findViewById(R.id.overdue_img_1);
                overdue_img_2 = (ImageView) itemView.findViewById(R.id.overdue_img_2);
                overdue_img_3 = (ImageView) itemView.findViewById(R.id.overdue_img_3);
                overdue_time = (TextView) itemView.findViewById(R.id.overdue_time);
                overdue_price = (TextView) itemView.findViewById(R.id.overdue_price);
                overdue_manger = (TextView) itemView.findViewById(R.id.overdue_manger);
                overdue_date = (TextView) itemView.findViewById(R.id.overdue_date);
                overdue_report = (TextView) itemView.findViewById(R.id.overdue_report);
                overdue_quantified = (TextView) itemView.findViewById(R.id.overdue_quantified);
            }

        }
    }
    ItemOperationListener itemOperationListener;

    public ItemOperationListener getItemOperationListener() {
        return itemOperationListener;
    }

    public void setItemOperationListener(ItemOperationListener itemOperationListener) {
        this.itemOperationListener = itemOperationListener;
    }

    public interface ItemOperationListener{
        void openMsg(int position);
        void openReport(int position);
        void openProgress(int position);
    }
}
