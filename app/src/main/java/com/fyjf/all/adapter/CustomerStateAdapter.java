package com.fyjf.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.dao.entity.CustomerState;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ASUS on 2017/6/27.
 */
/*
* author: renweiwei
* datetime: 
*/
public class CustomerStateAdapter extends android.widget.BaseAdapter {
    private List<CustomerState> list;
    private Context mContext;

    public CustomerStateAdapter(Context context, List<CustomerState> list) {
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
        ViewHoler holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_customer_state_item, null);
            holder = new ViewHoler();
            holder.tv_state_name = (TextView) convertView.findViewById(R.id.tv_state_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHoler) convertView.getTag();
        }
        holder.tv_state_name.setText(list.get(position).toString());
        return convertView;
    }

    private static class ViewHoler{
        public TextView tv_state_name;
    }
}
