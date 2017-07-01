package com.fyjf.all.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.fyjf.utils.StatisticsUtils;

import butterknife.ButterKnife;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/24-18:53
 * Email: renweiwei@ufashion.com
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected Context mContext;
    private FragmentManager fragmentManager;
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        this.getWindow().setFormat(2);
        setContentView(getContentLayout());
        mContext = BaseFragmentActivity.this;
        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);
        preInitData();
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

    protected void switchContent(Fragment to, String tag) {
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
    //content id eg: R.id.content
    protected abstract int getContentId();
}
