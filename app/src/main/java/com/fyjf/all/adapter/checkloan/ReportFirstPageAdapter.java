package com.fyjf.all.adapter.checkloan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fyjf.all.R;
import com.fyjf.dao.entity.LoanTime;
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
public class ReportFirstPageAdapter extends BaseRecyclerAdapter<ReportFirstPageAdapter.SimpleAdapterViewHolder> {

    private List<LoanTime> list;
    private Context mContext;
    private int customerState =1;

    public ReportFirstPageAdapter(Context context, List<LoanTime> list,int customerState) {
        this.list = list;
        this.mContext = context;
        this.customerState = customerState;
    }
    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_loan_check_item_copy, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        LoanTime item = list.get(position);
        holder.tv_date.setText(item.getYearMonth().substring(4,6)+"月");
        holder.tv_title.setText(item.getYearMonth().substring(0,4)+"年"+item.getYearMonth().substring(4,6)+"月份贷后检查");
        if(!TextUtils.isEmpty(item.getReportImages())){
            String sourceStr = item.getReportImages();
            String[] sourceStrArray = sourceStr.split(",");
            int imgs = 1;
            for (int i = 0; i < sourceStrArray.length; i++) {
                if(!TextUtils.isEmpty(sourceStrArray[i])){
                    if(imgs>3){
                        break;
                    }
                    if(imgs==1){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.image_1);
                    }else if(imgs==2){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.image_2);
                    }else if(imgs==3){
                        Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.image_3);
                    }
                    imgs++;
                }
            }
        }else {
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.image_1);
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.image_2);
            Glide.with(mContext).load(R.drawable.img_empty).into(holder.image_3);
        }
        holder.month_count.setText(item.getCount());
        holder.loan_item.setTag(position);
        holder.loan_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openMonthReport(position);
            }
        });

    }

    @Override
    public int getAdapterItemCount() {
        return list!=null?list.size():0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout loan_item;
        private TextView tv_date;
        private TextView tv_title;
        private TextView month_count;
        private ImageView image_1;
        private ImageView image_2;
        private ImageView image_3;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                loan_item = (LinearLayout) itemView.findViewById(R.id.loan_item);
                tv_date = (TextView) itemView.findViewById(R.id.tv_date);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                month_count = (TextView) itemView.findViewById(R.id.month_count);
                image_1 = (ImageView) itemView.findViewById(R.id.image_1);
                image_2 = (ImageView) itemView.findViewById(R.id.image_2);
                image_3 = (ImageView) itemView.findViewById(R.id.image_3);
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
        void openMonthReport(int position);
    }
}
