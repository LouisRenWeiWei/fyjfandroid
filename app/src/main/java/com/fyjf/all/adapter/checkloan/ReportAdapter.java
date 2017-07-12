package com.fyjf.all.adapter.checkloan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.dao.entity.LoanTime;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportAdapter extends BaseRecyclerAdapter<ReportAdapter.SimpleAdapterViewHolder> {

    private List<LoanTime> list;
    private Context mContext;

    public ReportAdapter(Context context, List<LoanTime> list) {
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
        LoanTime time = list.get(position);
        holder.month.setText(time.getYearMonth().substring(4,6)+"月");
        holder.month_title.setText(time.getYearMonth().substring(0,4)+"年"+time.getYearMonth().substring(4,6)+"月份贷后检查");
        holder.month_count.setText(time.getCount());


//        CustomerInfo customer = list.get(position);
//        holder.tv_customer_name.setText(customer.getName());
//        holder.tv_check_date.setText(customer.getExaminTime());
//        holder.btn_loan_check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openReport(position);
//            }
//        });
//        holder.btn_analysis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openAnalysisReport(position);
//            }
//        });
////        holder.btn_img_file.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if(itemOperationListener!=null)itemOperationListener.openImageReport(position);
////            }
////        });
//        holder.btn_credit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openCreditReport(position);
//            }
//        });
//        holder.ll_mgs.removeAllViews();
//        if(!TextUtils.isEmpty(customer.getReportImages())){
//            holder.ll_mgs.setVisibility(View.VISIBLE);
//            String[] imgs = customer.getReportImages().split(",");
//            int addCount = 0;
//
//            for(int i=0;i<imgs.length;i++){
//                String itemImgs = imgs[i];
//                if(!TextUtils.isEmpty(itemImgs)){
//                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_item,null);
//                    ImageView iv = (ImageView) view.findViewById(R.id.iv);
//                    String path = RequestUrl.img_file_upload+itemImgs;
//                    Glide.with(mContext).load(path).into(iv);
//                    holder.ll_mgs.addView(view);
//                    addCount++;
//                    if(addCount==3){
//                        break;
//                    }
//                }
//            }
//            if(addCount==0){
//                holder.ll_mgs.setVisibility(View.GONE);
//            }
//        }else{
//            holder.ll_mgs.setVisibility(View.GONE);
//        }
//        holder.ll_mgs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openImageReport(position);
//            }
//        });
    }

    @Override
    public int getAdapterItemCount() {
        return list!=null?list.size():0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView month;
        private TextView month_title;
        private TextView month_count;
        private ImageView image_1;
        private ImageView image_2;
        private ImageView image_3;

//        public TextView tv_customer_name;
//        public TextView tv_check_date;
//        public Button btn_loan_check;
//        public Button btn_analysis;
////        public Button btn_img_file;
//        public Button btn_credit;
//        public LinearLayout ll_mgs;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                month = (TextView) itemView.findViewById(R.id.month);
                month_title = (TextView) itemView.findViewById(R.id.month_title);
                month_count = (TextView) itemView.findViewById(R.id.month_count);
                image_1 = (ImageView) itemView.findViewById(R.id.image_1);
                image_2 = (ImageView) itemView.findViewById(R.id.image_2);
                image_3 = (ImageView) itemView.findViewById(R.id.image_3);

                //                tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
//                tv_check_date = (TextView) itemView.findViewById(R.id.tv_check_date);
//                btn_loan_check = (Button) itemView.findViewById(R.id.btn_loan_check);
//                btn_analysis = (Button) itemView.findViewById(R.id.btn_analysis);
////                btn_img_file = (Button) itemView.findViewById(R.id.btn_img_file);
//                btn_credit = (Button) itemView.findViewById(R.id.btn_credit);
//                ll_mgs = (LinearLayout) itemView.findViewById(R.id.ll_mgs);
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
