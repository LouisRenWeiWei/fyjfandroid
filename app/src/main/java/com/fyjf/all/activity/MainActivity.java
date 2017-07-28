package com.fyjf.all.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.activity.bank.BankAnalysisActivity;
import com.fyjf.all.activity.overdue.OverdueActivity;
import com.fyjf.all.activity.report.ReportMainActivity;
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
                startActivity(ReportMainActivity.class);//默认是1
                break;
            case R.id.btn_waring:
                Intent intent = new Intent(MainActivity.this,ReportMainActivity.class);
                intent.putExtra("customerState",2);
                startActivity(intent);
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

    private long exitTime = 0;
    /**
     * 拦截返回按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitOnSecondTime();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 三秒内两次点击 以退出(回桌面)
     */
    private void exitOnSecondTime() {
        if (System.currentTimeMillis() - exitTime <= 3000) {
            goHome();
            exitTime = 0;
        } else {
            ToastUtils.showSystemToast(mContext,"再次点击返回退出");
            exitTime = System.currentTimeMillis();
        }
    }

    /**
     * 模拟返回桌面
     */
    public void goHome() {
        moveTaskToBack(true);
    }
}
