package com.fyjf.all.adapter;


/**
 * Created by 任伟伟
 * Datetime: 2017/2/10-11:47
 * Email: renweiwei@ufashion.com
 */

public abstract class BaseViewHolder<T> {
    protected BaseAdapter adapter;

    public BaseViewHolder(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract void setData(int position, T data);
}
