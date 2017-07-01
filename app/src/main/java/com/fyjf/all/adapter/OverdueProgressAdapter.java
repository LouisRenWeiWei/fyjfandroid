package com.fyjf.all.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fyjf.all.R;
import com.fyjf.all.adapter.checkloan.ReportImagAdapter;
import com.fyjf.dao.entity.ImageFile;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;
import com.fyjf.widget.refreshview.recyclerview.GridVerticalHorizonSpace;

import java.util.ArrayList;
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
        OverdueProgress item = list.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_desc.setText(item.getDescription());
        if (!TextUtils.isEmpty(item.getOverdueImgs())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
            holder.recyclerView.setLayoutManager(gridLayoutManager);// 布局管理器。
            holder.recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
            holder.recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
            holder.recyclerView.addItemDecoration(new GridVerticalHorizonSpace(30, 30));
            String[] imgs = item.getOverdueImgs().split(",");
            List<ImageFile> imageFiles = new ArrayList<>();
            for(int i=0;i<imgs.length;i++){
                ImageFile img = new ImageFile();
                img.setUrl(RequestUrl.img_file_upload+imgs[i]);
                imageFiles.add(img);
            }
            ReportImagAdapter imagAdapter = new ReportImagAdapter(mContext, imageFiles);
            holder.recyclerView.setAdapter(imagAdapter);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public RecyclerView recyclerView;
        public TextView tv_desc;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
                tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
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
    }
}
