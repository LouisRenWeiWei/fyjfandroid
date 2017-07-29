package com.fyjf.all.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.fyjf.utils.StatisticsUtils;
import com.wwren.dialog.SweetAlert.SweetAlertDialog;

import butterknife.ButterKnife;


/**
 * Created by 任伟伟
 * Datetime: 2016/10/24-18:53
 * Email: renweiwei@ufashion.com
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected Context mContext;
    protected SweetAlertDialog dialog;
    private FragmentManager fragmentManager;
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        this.getWindow().setFormat(2);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        mContext = BaseFragmentActivity.this;
//        PushAgent.getInstance(mContext).onAppStart();
        fragmentManager = getSupportFragmentManager();
        preInitData();
    }

    protected void showDialog(String msg){
        if(dialog==null){
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setCancelable(false);
        }
        dialog.setTitleText(msg);
        dialog.show();
    }

    protected void dismisDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismissWithAnimation();
        }
    }


    protected abstract int getContentLayout();

    protected abstract void preInitData();

    @Override
    protected void onResume() {
        super.onResume();
        StatisticsUtils.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsUtils.onPause(this);
    }

    protected Fragment findFragmentByTag(String tag){
        return fragmentManager.findFragmentByTag(tag);
    }

    protected abstract void showFragment(String tag);

    protected void switchContent(Fragment to,String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mContent!=null&&to != mContent) {
            if (!to.isAdded()) {
                transaction.hide(mContent).add(getContentId(), to, tag).commit();
            } else {
                transaction.hide(mContent).show(to).commit();
            }
        }else {
            if(mContent==null){
                if (!to.isAdded()) {
                    transaction.add(getContentId(), to, tag).commit();
                }
            }else {//相等的情况
                transaction.show(to).commit();
            }
        }
        mContent = to;
        transaction = null;
    }

    protected abstract int getContentId();
}
