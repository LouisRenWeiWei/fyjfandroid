package com.fyjf.all.activity.common;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.widget.photoview.PhotoView;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/7/23.
 */

public class ImageActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.photoView)
    PhotoView photoView;
    private String title;
    private String url;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_image_show;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        if (!TextUtils.isEmpty(title)) tv_title.setText(title);
        url = intent.getStringExtra("url");
        if (!TextUtils.isEmpty(url)) Glide.with(mContext).load(url).into(photoView);

    }
}
