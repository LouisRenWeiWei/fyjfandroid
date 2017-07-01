package com.fyjf.all.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fyjf.all.R;


/**
 * Created by 任伟伟
 * Datetime: 2016/11/17-11:20
 * Email: renweiwei@ufashion.com
 */

public class SelectOrTakePhotoView extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private LinearLayout ll_select_take;
    private TextView tv_select_photos;
    private TextView tv_take_photos;
    private TextView tv_cancel;
    public SelectOrTakePhotoView(Context context) {
        this(context,null);
    }

    public SelectOrTakePhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.widget_select_or_take_photo,this);
        ll_select_take = (LinearLayout) findViewById(R.id.ll_select_take);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_select_photos = (TextView) findViewById(R.id.tv_select_photos);
        tv_take_photos = (TextView) findViewById(R.id.tv_take_photos);
        ll_select_take.setOnClickListener(this);
        tv_select_photos.setOnClickListener(this);
        tv_take_photos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_select_take:
            case R.id.tv_cancel:
                setVisibility(GONE);
                break;
            case R.id.tv_select_photos:
                setVisibility(GONE);
                if(operationListener!=null)operationListener.selectPhoto();
                break;
            case R.id.tv_take_photos:
                setVisibility(GONE);
                if(operationListener!=null)operationListener.takePhoto();
                break;
        }
    }

    public static interface OperationListener{
        void selectPhoto();
        void takePhoto();
    }
    private OperationListener operationListener;

    public OperationListener getOperationListener() {
        return operationListener;
    }

    public void setOperationListener(OperationListener operationListener) {
        this.operationListener = operationListener;
    }
}
