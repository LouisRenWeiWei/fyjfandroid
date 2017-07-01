package com.fyjf.all.activity.report;

import android.text.TextUtils;
import android.view.View;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.widget.SimpleWebView;
import com.fyjf.vo.RequestUrl;
import com.rey.material.widget.ImageView;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class ReportAnalysisActivity extends BaseActivity{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.simpleWebView)
    SimpleWebView simpleWebView;
    private String customerId;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_report_analysis;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        customerId = getIntent().getStringExtra("customerId");
        if(!TextUtils.isEmpty(customerId)){
            simpleWebView.loadUrl(RequestUrl.customer_report_analysis+customerId);
        }

    }
}
