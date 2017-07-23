package com.fyjf.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.dao.entity.OverdueMessageBean;
import com.fyjf.dao.entity.ReportMessageBean;
import com.fyjf.dao.utils.TimeUtil;
import com.fyjf.widget.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by czf on 2017/7/15.
 */

public class OverdueMsgAdapter extends BaseRecyclerAdapter<OverdueMsgAdapter.SimpleAdapterViewHolder> {

    private List<OverdueMessageBean> list;
    private Context mContext;

    public OverdueMsgAdapter(Context context, List<OverdueMessageBean> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_overdue_msg_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, final int position, boolean isItem) {
        OverdueMessageBean time = list.get(position);
        holder.msg_user_name.setText(time.getMessagerName());
        holder.msg_time.setText(TimeUtil.timeHao2Date(time.getCreateDate(),"yyyy-MM-dd"));
        holder.msg_context.setText(time.getContent());
        holder.msg_item.setTag(position);

    }

    @Override
    public int getAdapterItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout msg_item;
        private TextView msg_user_name;
        private TextView msg_time;
        private TextView msg_context;
        private LinearLayout ll_replies;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                ll_replies = (LinearLayout) itemView.findViewById(R.id.ll_replies);
                msg_item = (RelativeLayout) itemView.findViewById(R.id.msg_item);
                msg_user_name = (TextView) itemView.findViewById(R.id.msg_user_name);
                msg_time = (TextView) itemView.findViewById(R.id.msg_time);
                msg_context = (TextView) itemView.findViewById(R.id.msg_context);

            }
        }

    }


}
