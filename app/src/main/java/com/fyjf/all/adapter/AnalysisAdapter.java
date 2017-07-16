package com.fyjf.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.dao.entity.AnalysisBean;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by czf on 2017/7/16.
 */

public class AnalysisAdapter extends BaseRecyclerAdapter<AnalysisAdapter.SimpleAdapterViewHolder> {
    private List<AnalysisBean> list;
    private Context mContext;

    public AnalysisAdapter(Context context, List<AnalysisBean> list) {
        this.list = list;
        this.mContext = context;
    }
    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.analysis_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        AnalysisBean overdueReport = list.get(position);
        holder.analysis_title.setText(overdueReport.getTitle());
        holder.analysis_msg.setText(overdueReport.getMsgCount());
        holder.analysis_item.setTag(position);
        onClick(holder, position);

    }

    private void onClick(SimpleAdapterViewHolder holder, final int position) {
        holder.analysis_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOperationListener!=null)itemOperationListener.openItem(position);
            }
        });
        holder.analysis_msg.setOnClickListener(new View.OnClickListener() {
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
        public RelativeLayout analysis_item;
        public TextView analysis_title;
        public TextView analysis_msg;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                analysis_item = (RelativeLayout) itemView.findViewById(R.id.analysis_item);
                analysis_title = (TextView) itemView.findViewById(R.id.analysis_title);
                analysis_msg = (TextView) itemView.findViewById(R.id.analysis_msg);
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
        void openItem(int position);
    }
}
