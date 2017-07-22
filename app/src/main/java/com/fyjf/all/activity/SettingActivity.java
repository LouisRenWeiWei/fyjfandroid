package com.fyjf.all.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.app.AppData;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.msg_select)
    CheckBox msg_select;
    @BindView(R.id.pwd_update)
    TextView pwd_update;
    @BindView(R.id.us_contact)
    TextView us_contact;

    @BindView(R.id.tv_logout)
    TextView tv_logout;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void preInitData() {
        msg_select.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.back,R.id.pwd_update,R.id.us_contact,R.id.tv_logout})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.pwd_update:
                startActivity(UpdatePwdActivity.class);
                break;
            case R.id.us_contact:
                startActivity(ContactUsActivity.class);
                break;
            case R.id.tv_logout:
                AppData.saveString(AppData.ACCOUNT,"");
                AppData.saveString(AppData.PASSWORD,"");
                startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView==msg_select){

        }
    }
}
