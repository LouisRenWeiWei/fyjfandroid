package com.fyjf.all.activity.common;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class ContactUsActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_app_version)
    TextView tv_app_version;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_app_version.setText("app版本：v"+AppUtils.getAppVersionName());
    }
}
