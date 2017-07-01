package com.fyjf.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import com.fyjf.all.R;

import java.util.List;

/**
 * Created by 任伟伟
 * Datetime: 2016/11/18-19:38
 * Email: renweiwei@ufashion.com
 */

public class SingleChoiceAdapter extends android.widget.BaseAdapter {
    private List list;
    private Context context;
    private int selection =-1;

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public SingleChoiceAdapter(Context context, List list) {
        this.list = list;
        this.context = context;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dialog_single_choice_item, null);
            holder = new ViewHolder();
            holder.rgbtn = (RadioButton) convertView.findViewById(R.id.rgbtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        boolean checked = selection==position;
        holder.rgbtn.setChecked(checked);
        holder.rgbtn.setText(list.get(position).toString());
        return convertView;
    }

    static class ViewHolder {
        public RadioButton rgbtn;
    }
}
