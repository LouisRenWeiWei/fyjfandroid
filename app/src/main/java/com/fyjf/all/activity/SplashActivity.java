package com.fyjf.all.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.fyjf.all.R;
import com.fyjf.all.app.AppData;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void preInitData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String account = AppData.getString(AppData.ACCOUNT);
                if (TextUtils.isEmpty(account)) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}
