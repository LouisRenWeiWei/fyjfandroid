package com.fyjf.all.activity;

import android.view.View;

import com.fyjf.all.R;
import com.fyjf.all.activity.overdue.OverdueActivity;
import com.fyjf.all.update.UpdateHelper;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.vo.RequestUrl;
import com.rey.material.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_loan_check)
    Button btn_loan_check;
    @BindView(R.id.btn_waring)
    Button btn_waring;
    @BindView(R.id.btn_overdue)
    Button btn_overdue;
    @BindView(R.id.btn_camera)
    Button btn_camera;
    @BindView(R.id.btn_atall_analysis)
    Button btn_atall_analysis;
    @BindView(R.id.btn_atall_query)
    Button btn_atall_query;
    @BindView(R.id.btn_news)
    Button btn_news;
    @BindView(R.id.btn_call_center)
    Button btn_call_center;
    @BindView(R.id.btn_contact_us)
    Button btn_contact_us;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void preInitData() {
        UpdateHelper updateHelper = new UpdateHelper.Builder(this)
                .checkUrl(RequestUrl.version_check)
                //.isHintNewVersion(false)    //没有新版本时不提示
                .isAutoInstall(true)    //设置为false需在下载完手动点击安装;默认值为true，下载后自动安装。
                .build();
        updateHelper.check();
    }

    @OnClick({R.id.btn_loan_check,R.id.btn_waring,R.id.btn_overdue,
            R.id.btn_camera,R.id.btn_atall_analysis,R.id.btn_atall_query,
            R.id.btn_news,R.id.btn_call_center,R.id.btn_contact_us
    })
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_loan_check:
                startActivity(ReportActivity.class);
                break;
            case R.id.btn_waring:
                startActivity(WaringActivity.class);
                break;
            case R.id.btn_overdue:
                startActivity(OverdueActivity.class);
                break;
            case R.id.btn_camera:
                ToastUtils.showSystemToast(mContext,getString(R.string.feature_during_develop));
                break;
            case R.id.btn_atall_analysis:
                ToastUtils.showSystemToast(mContext,getString(R.string.feature_during_develop));
                break;
            case R.id.btn_atall_query:
                startActivity(AtallQueryActivity.class);
                break;
            case R.id.btn_news:
                ToastUtils.showSystemToast(mContext,getString(R.string.feature_during_develop));
                break;
            case R.id.btn_call_center:
                ToastUtils.showSystemToast(mContext,getString(R.string.feature_during_develop));
                break;
            case R.id.btn_contact_us:
                startActivity(ContactUsActivity.class);
                break;
        }
    }
}
