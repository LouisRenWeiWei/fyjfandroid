package com.fyjf.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fyjf.all.R;
import com.fyjf.dao.entity.Customer;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.utils.TimeUtils;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;
import com.rey.material.widget.Button;

import java.util.ArrayList;
import java.util.Date;
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
        holder.tv_customer_name.setText(overdueReport.getCustomer().getName());
        if(TextUtils.isEmpty(overdueReport.getOverdueDays())){
            overdueReport.setOverdueDays("0");
        }
        int overdueDays = Integer.parseInt(overdueReport.getOverdueDays());
        holder.tv_check_date.setText("逾期："+(overdueDays+ TimeUtils.getBetweenDay(TimeUtils.parse(overdueReport.getCreateDate(),"yyyy-MM-dd HH:mm:ss"),new Date()))+"天");

        holder.btn_loan_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openReport(position);
            }
        });
        holder.btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openProgress(position);
            }
        });
        List imgs = new ArrayList<String>();
        if(overdueReport.getOverdueProgress()!=null){
            for(int i=0;i<overdueReport.getOverdueProgress().size();i++){
                OverdueProgress progress = overdueReport.getOverdueProgress().get(i);
                if(imgs.size()<3&&!TextUtils.isEmpty(progress.getOverdueImgs())){
                    String[] tmps = progress.getOverdueImgs().split(",");
                    for(int j=0;j<tmps.length;j++){
                        if(imgs.size()<3){
                            imgs.add(tmps[j]);
                        }else{
                            break;
                        }
                    }
                }
                if(imgs.size()==3){
                    break;
                }
            }
        }

        holder.ll_mgs.removeAllViews();
        for(int i=0;i<imgs.size();i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_item,null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            String path = RequestUrl.img_file_upload+imgs.get(i);
            Glide.with(mContext).load(path).into(iv);
            holder.ll_mgs.addView(view);

        }



    }

    @Override
    public int getAdapterItemCount() {
        return list!=null?list.size():0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_customer_name;
        public TextView tv_check_date;
        public Button btn_loan_check;
        public Button btn_analysis;
        public LinearLayout ll_mgs;
//        public ImageView iv_one;
//        public ImageView iv_two;
//        public ImageView iv_three;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
                tv_check_date = (TextView) itemView.findViewById(R.id.tv_check_date);
                btn_loan_check = (Button) itemView.findViewById(R.id.btn_loan_check);
                btn_analysis = (Button) itemView.findViewById(R.id.btn_analysis);
                ll_mgs = (LinearLayout) itemView.findViewById(R.id.ll_mgs);
//                iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
//                iv_two = (ImageView) itemView.findViewById(R.id.iv_two);
//                iv_three = (ImageView) itemView.findViewById(R.id.iv_three);
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
        void openReport(int position);
        void openProgress(int position);
    }
}
