package com.fyjf.all.adapter.checkloan;

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
import com.fyjf.dao.entity.CustomerInfo;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;
import com.rey.material.widget.Button;

import java.util.List;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class CheckLoanCustomerAdapter extends BaseRecyclerAdapter<CheckLoanCustomerAdapter.SimpleAdapterViewHolder> {

    private List<CustomerInfo> list;
    private Context mContext;

    public CheckLoanCustomerAdapter(Context context, List<CustomerInfo> list) {
        this.list = list;
        this.mContext = context;
    }
    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_loan_check_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        CustomerInfo customer = list.get(position);
        holder.tv_customer_name.setText(customer.getName());
        holder.tv_check_date.setText(customer.getExaminTime());
        holder.btn_loan_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openReport(position);
            }
        });
        holder.btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openAnalysisReport(position);
            }
        });
//        holder.btn_img_file.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openImageReport(position);
//            }
//        });
        holder.btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openCreditReport(position);
            }
        });
        holder.ll_mgs.removeAllViews();
        if(!TextUtils.isEmpty(customer.getReportImages())){
            holder.ll_mgs.setVisibility(View.VISIBLE);
            String[] imgs = customer.getReportImages().split(",");
            int addCount = 0;

            for(int i=0;i<imgs.length;i++){
                String itemImgs = imgs[i];
                if(!TextUtils.isEmpty(itemImgs)){
                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_item,null);
                    ImageView iv = (ImageView) view.findViewById(R.id.iv);
                    String path = RequestUrl.img_file_upload+itemImgs;
                    Glide.with(mContext).load(path).into(iv);
                    holder.ll_mgs.addView(view);
                    addCount++;
                    if(addCount==3){
                        break;
                    }
                }
            }
            if(addCount==0){
                holder.ll_mgs.setVisibility(View.GONE);
            }
        }else{
            holder.ll_mgs.setVisibility(View.GONE);
        }
        holder.ll_mgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openImageReport(position);
            }
        });
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
//        public Button btn_img_file;
        public Button btn_credit;
        public LinearLayout ll_mgs;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
                tv_check_date = (TextView) itemView.findViewById(R.id.tv_check_date);
                btn_loan_check = (Button) itemView.findViewById(R.id.btn_loan_check);
                btn_analysis = (Button) itemView.findViewById(R.id.btn_analysis);
//                btn_img_file = (Button) itemView.findViewById(R.id.btn_img_file);
                btn_credit = (Button) itemView.findViewById(R.id.btn_credit);
                ll_mgs = (LinearLayout) itemView.findViewById(R.id.ll_mgs);
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
        void openCreditReport(int position);
        void openImageReport(int position);
        void openAnalysisReport(int position);
    }
}
