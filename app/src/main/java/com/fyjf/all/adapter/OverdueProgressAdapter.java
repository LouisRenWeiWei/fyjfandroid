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
import com.fyjf.dao.entity.OverdueProgress;
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
public class OverdueProgressAdapter extends BaseRecyclerAdapter<OverdueProgressAdapter.SimpleAdapterViewHolder> {

    private List<OverdueProgress> list;
    private Context mContext;

    public OverdueProgressAdapter(Context context, List<OverdueProgress> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_overdue_progress_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        OverdueProgress time = list.get(position);
        holder.month.setText(time.getCreateDate().substring(5,7)+"月");
        holder.month_title.setText(time.getTitle());
        holder.month_count.setText("0");
        holder.tips.setText(time.getDescription());
        String sourceStr = time.getOverdueImgs();
        String[] sourceStrArray = sourceStr.split(",");
        int imgs = 1;
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(!TextUtils.isEmpty(sourceStrArray[i])){
                if(imgs>3){
                    break;
                }
                if(imgs==1){
                    Glide.with(mContext).load(RequestUrl.file_base + sourceStrArray[i]).into(holder.image_1);
                }else if(imgs==2){
                    Glide.with(mContext).load(RequestUrl.file_base + sourceStrArray[i]).into(holder.image_2);
                }else if(imgs==3){
                    Glide.with(mContext).load(RequestUrl.file_base + sourceStrArray[i]).into(holder.image_3);
                }
                imgs++;
            }
        }

        holder.loan_item.setTag(position);
        holder.loan_item.setOnClickListener(new View.OnClickListener() {
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
        private RelativeLayout loan_item;
        private TextView month;
        private TextView month_title;
        private TextView month_count;
        private ImageView image_1;
        private ImageView image_2;
        private ImageView image_3;
        private TextView tips;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                loan_item = (RelativeLayout) itemView.findViewById(R.id.loan_item);
                month = (TextView) itemView.findViewById(R.id.month);
                month_title = (TextView) itemView.findViewById(R.id.month_title);
                month_count = (TextView) itemView.findViewById(R.id.month_count);
                tips = (TextView) itemView.findViewById(R.id.tips);
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
        void openReport(int position);
    }
}
