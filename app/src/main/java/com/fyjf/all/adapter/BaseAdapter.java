package com.fyjf.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/26-11:07
 * Email: renweiwei@ufashion.com
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> list;
    private Context mContext;

    public BaseAdapter(Context context, List<T> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getContentLayout(), null);
            holder = getHolder(BaseAdapter.this);
            ButterKnife.bind(holder, convertView);//用butterKnife绑定
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        holder.setData(position,list.get(position));
        return convertView;
    }
    protected abstract int getContentLayout();
    protected abstract BaseViewHolder getHolder(BaseAdapter adapter);
}
