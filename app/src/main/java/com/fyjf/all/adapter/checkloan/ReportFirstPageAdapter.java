package com.fyjf.all.adapter.checkloan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class ReportFirstPageAdapter extends BaseRecyclerAdapter<ReportFirstPageAdapter.SimpleAdapterViewHolder> {

    private List<LoanTime> list;
    private Context mContext;

    public ReportFirstPageAdapter(Context context, List<LoanTime> list) {
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
        private RelativeLayout loan_item;
        private TextView month;
        private TextView month_title;
        private TextView month_count;
        private ImageView image_1;
        private ImageView image_2;
        private ImageView image_3;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                loan_item = (RelativeLayout) itemView.findViewById(R.id.loan_item);
                month = (TextView) itemView.findViewById(R.id.month);
                month_title = (TextView) itemView.findViewById(R.id.month_title);
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
