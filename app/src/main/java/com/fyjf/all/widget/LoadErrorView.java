package com.fyjf.all.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.fyjf.all.R;
import com.fyjf.utils.NetworkUtils;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/25-13:29
 * Email: renweiwei@ufashion.com
 */

public class LoadErrorView extends LinearLayout {
    private TextView tv_promote;
    private ImageView iv_error,iv_reload;
    public LoadErrorView(Context context) {
        super(context);
    }
    private Context mContext;

    public LoadErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.widget_load_error,this);
        tv_promote = (TextView) findViewById(R.id.tv_promote);
        iv_reload = (ImageView) findViewById(R.id.iv_reload);
        iv_error = (ImageView) findViewById(R.id.iv_error);
        iv_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(iv_error, "translationY",0,-150,0).setDuration(300).start();
                if (onReloadListener!=null)onReloadListener.onReload();
            }
        });
    }
    public void setPromte(String promte){
        tv_promote.setText(promte);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(View.VISIBLE==visibility){
            if(NetworkUtils.isNetworkAvailable(mContext)){
                setPromte(mContext.getString(R.string.network_error_load));
            }else {
                setPromte(mContext.getString(R.string.network_error));
            }
        }
    }
    public static interface OnReloadListener{
        void onReload();
    }
    private OnReloadListener onReloadListener;

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }
}