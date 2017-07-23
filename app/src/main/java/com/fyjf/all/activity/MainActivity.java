package com.fyjf.all.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.activity.overdue.OverdueActivity;
import com.fyjf.all.update.UpdateHelper;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.vo.RequestUrl;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_loan_check)
    TextView btn_loan_check;
    @BindView(R.id.btn_waring)
    TextView btn_waring;
    @BindView(R.id.btn_overdue)
    TextView btn_overdue;
    @BindView(R.id.btn_camera)
    TextView btn_camera;
    @BindView(R.id.btn_atall_analysis)
    TextView btn_atall_analysis;
    @BindView(R.id.btn_atall_query)
    TextView btn_atall_query;
    @BindView(R.id.setting)
    ImageView setting;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void preInitData() {
        UpdateHelper updateHelper = new UpdateHelper.Builder(this)
                .checkUrl(RequestUrl.version_check)
                .isHintNewVersion(false)    //没有新版本时不提示
                .isAutoInstall(true)    //设置为false需在下载完手动点击安装;默认值为true，下载后自动安装。
                .build();
        updateHelper.check();
    }

    @OnClick({R.id.btn_loan_check,R.id.btn_waring,R.id.btn_overdue,
            R.id.btn_camera,R.id.btn_atall_analysis,R.id.btn_atall_query,R.id.setting
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
                startActivity(BankAnalysisActivity.class);
                break;
            case R.id.btn_atall_query:
                startActivity(AtallQueryActivity.class);
                break;
            case R.id.setting:
                startActivity(SettingActivity.class);
                break;
        }
    }
}
