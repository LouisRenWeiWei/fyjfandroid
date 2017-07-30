package com.fyjf.all.activity.report;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.widget.SimpleWebView;
import com.fyjf.vo.RequestUrl;
import com.fyjf.widget.refreshview.utils.LogUtils;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class ReportEchartsActivity extends BaseActivity implements SimpleWebView.WebViewListener{
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
        simpleWebView.setWebViewListener(this);
        customerId = getIntent().getStringExtra("reportId");
        if(!TextUtils.isEmpty(customerId)){
            showDialog("正在加载，请稍后");
            simpleWebView.loadUrl(RequestUrl.customer_report_analysis+customerId);
            LogUtils.e("url:"+RequestUrl.customer_report_analysis+customerId);
        }

    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onError() {
        dismisDialog();
    }

    @Override
    public void onFinish() {
        dismisDialog();
    }
}