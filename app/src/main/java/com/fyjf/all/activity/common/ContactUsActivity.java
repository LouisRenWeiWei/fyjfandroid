package com.fyjf.all.activity.common;

import android.view.View;
import android.widget.ImageView;

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
    }
}
