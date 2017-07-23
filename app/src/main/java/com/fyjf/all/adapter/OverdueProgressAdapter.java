package com.fyjf.all.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.fyjf.all.activity.ImageActivity;
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
        if(position==0)holder.v_top_line.setVisibility(View.INVISIBLE);
        OverdueProgress item = list.get(position);
        holder.tv_money.setText(item.getMoney()+"万");
        holder.month.setText(item.getCreateDate().substring(5,7)+"月");
        holder.month_title.setText(item.getTitle());
        holder.month_count.setText("0");
        holder.tips.setText(item.getDescription());
        String sourceStr = item.getOverdueImgs();
        holder.ll_imgs.removeAllViews();
        if(!TextUtils.isEmpty(sourceStr)){
            String[] sourceStrArray = sourceStr.split(",");
            int addSize = 0;
            for (int i = 0; i < sourceStrArray.length; i++) {
                String url = RequestUrl.file_image + sourceStrArray[i];
                View view = addImage(url);
                view.setTag(url);
                holder.ll_imgs.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ImageActivity.class);
                        intent.putExtra("url",v.getTag().toString());
                        mContext.startActivity(intent);
                    }
                });
                addSize++;
            }
            while (addSize<2){
                holder.ll_imgs.addView(addImageDefault());
                addSize++;
            }
        }else {
            //添加三个默认的
            holder.ll_imgs.addView(addImageDefault());
            holder.ll_imgs.addView(addImageDefault());
            holder.ll_imgs.addView(addImageDefault());
        }


        holder.loan_item.setTag(position);
//        holder.loan_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(itemOperationListener!=null)itemOperationListener.openReport(position);
//            }
//        });
        holder.month_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openMsg(position);
            }
        });

    }

    @Override
    public int getAdapterItemCount() {
        return list!=null?list.size():0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout loan_item;
        private View v_top_line;
        private TextView month;
        private TextView tv_money;
        private TextView month_title;
        private TextView month_count;
        private TextView tips;
        private LinearLayout ll_imgs;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                tv_money = (TextView) itemView.findViewById(R.id.tv_money);
                loan_item = (LinearLayout) itemView.findViewById(R.id.loan_item);
                v_top_line = itemView.findViewById(R.id.v_top_line);
                month = (TextView) itemView.findViewById(R.id.month);
                month_title = (TextView) itemView.findViewById(R.id.month_title);
                month_count = (TextView) itemView.findViewById(R.id.month_count);
                tips = (TextView) itemView.findViewById(R.id.tips);
                ll_imgs = (LinearLayout) itemView.findViewById(R.id.ll_imgs);
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
//        void openReport(int position);
        void openMsg(int position);
    }

    private View addImage(String url){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        Glide.with(mContext).load(url).into(imageView);
        return view;
    }
    private View addImageDefault(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        Glide.with(mContext).load(R.drawable.img_empty).into(imageView);
        return view;
    }
}
