package com.fyjf.all.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.fyjf.utils.StatisticsUtils;

import butterknife.ButterKnife;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/24-18:53
 * Email: renweiwei@ufashion.com
 */

public abstract class BaseActivity extends Activity {
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        mContext = BaseActivity.this;
        ButterKnife.bind(this);
        preInitData();
    }

    protected abstract int getContentLayout();

    protected abstract void preInitData();


    @Override
    protected void onResume() {
        super.onResume();
        StatisticsUtils.onResume(this,this.getComponentName().getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsUtils.onPause(this,this.getComponentName().getClass().getSimpleName());
    }

    protected <T extends Activity> void  startActivity(Class<T> clazz){
        Intent intent = new Intent(mContext,clazz);
        startActivity(intent);
    }
    protected <T extends Activity> void  startActivity(Class<T> clazz,Bundle bundle){
        Intent intent = new Intent(mContext,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    protected <T extends Activity> void  startActivityForResult(Class<T> clazz,int requestCode){
        Intent intent = new Intent(mContext,clazz);
        startActivityForResult(intent,requestCode);
    }
    protected <T extends Activity> void  startActivityForResult(Class<T> clazz,Bundle bundle,int requestCode){
        Intent intent = new Intent(mContext,clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }
}
