package com.fyjf.all.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.fyjf.dao.entity.CustomerReportInfo;
import com.fyjf.utils.TimeUtils;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by czf on 2017/7/13.
 */

public class ReportDetailsAdapter extends BaseRecyclerAdapter<ReportDetailsAdapter.SimpleAdapterViewHolder> {

    private List<CustomerReportInfo> list;
    private Context mContext;
    private int customerState =1;

    public ReportDetailsAdapter(Context context, List<CustomerReportInfo> list, int customerState) {
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
        View v = LayoutInflater.from(mContext).inflate(R.layout.report_first_page_detail_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        CustomerReportInfo item = list.get(position);
        if(1==customerState){
            holder.tv_state.setVisibility(View.GONE);
        }else {
            holder.tv_state.setVisibility(View.VISIBLE);
        }
        holder.tv_date.setText(TimeUtils.formateDate(item.getExaminTime(),"yyyy-MM-dd","MM月dd日"));
        holder.customer_name.setText(item.getCustomerName());
        holder.customer_msg.setText(item.getMsgCount());
        holder.customer_manager.setText(item.getCustomerManager());
        holder.customer_time.setText(item.getExaminTime());
        if("1".equals(item.getCustomerLoanType())){
            holder.customer_type.setText("抵押贷款");
            holder.customer_type.setTextColor(Color.parseColor("#3A558E"));
        }else if("2".equals(item.getCustomerLoanType())){
            holder.customer_type.setText("担保贷款");
            holder.customer_type.setTextColor(Color.parseColor("#0BA422"));
        }else if("3".equals(item.getCustomerLoanType())){
            holder.customer_type.setText("信用贷款");
            holder.customer_type.setTextColor(Color.parseColor("#FF4081"));
        }else {
            holder.customer_type.setText("");
        }


        String sourceStr = item.getReportImages();
        String[] sourceStrArray = sourceStr.split(",");
        int imgs = 1;
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(!TextUtils.isEmpty(sourceStrArray[i])){
                if(imgs>3){
                    break;
                }
                if(imgs==1){
                    Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.customer_img_1);
                }else if(imgs==2){
                    Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.customer_img_2);
                }else if(imgs==3){
                    Glide.with(mContext).load(RequestUrl.file_image + sourceStrArray[i]).into(holder.customer_img_3);
                }
                imgs++;
            }
        }
        holder.customer_item.setTag(position);

        onClick(holder, position);

    }

    private void onClick(SimpleAdapterViewHolder holder, final int position) {
        holder.customer_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openMsg(position);
            }
        });
        holder.customer_img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openImg(position);
            }
        });
        holder.customer_img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openImg(position);
            }
        });
        holder.customer_img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openImg(position);
            }
        });
        holder.ll_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openPDF(position);
            }
        });
        holder.ll_analysic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openQuantified(position);
            }
        });
        holder.ll_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openCredit(position);
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout customer_item;
        private TextView tv_state;
        private TextView tv_date;
        private TextView customer_name;
        private TextView customer_msg;
        private ImageView customer_img_1;
        private ImageView customer_img_2;
        private ImageView customer_img_3;
        private TextView customer_type;
        private TextView customer_manager;
        private TextView customer_time;
        private LinearLayout ll_report;
        private LinearLayout ll_analysic;
        private LinearLayout ll_credit;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                tv_state = (TextView) itemView.findViewById(R.id.tv_state);
                tv_date = (TextView) itemView.findViewById(R.id.tv_date);
                customer_type = (TextView) itemView.findViewById(R.id.customer_type);
                customer_item = (RelativeLayout) itemView.findViewById(R.id.customer_item);
                customer_name = (TextView) itemView.findViewById(R.id.customer_name);
                customer_msg = (TextView) itemView.findViewById(R.id.customer_msg);
                customer_img_1 = (ImageView) itemView.findViewById(R.id.customer_img_1);
                customer_img_2 = (ImageView) itemView.findViewById(R.id.customer_img_2);
                customer_img_3 = (ImageView) itemView.findViewById(R.id.customer_img_3);
                customer_manager = (TextView) itemView.findViewById(R.id.customer_manager);
                customer_time = (TextView) itemView.findViewById(R.id.customer_time);
                ll_report = (LinearLayout) itemView.findViewById(R.id.ll_report);
                ll_analysic = (LinearLayout) itemView.findViewById(R.id.ll_analysic);
                ll_credit = (LinearLayout) itemView.findViewById(R.id.ll_credit);
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

    public interface ItemOperationListener {
        void openMsg(int position);
        void openImg(int position);
        void openPDF(int position);
        void openQuantified(int position);
        void openCredit(int position);
    }
}
