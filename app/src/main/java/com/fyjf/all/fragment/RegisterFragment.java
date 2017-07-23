package com.fyjf.all.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ASUS on 2017/5/17.
 */

public class RegisterFragment extends BaseFragment {
    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirm_password)
    EditText et_confirm_password;
    @BindView(R.id.tv_register)
    TextView tv_register;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_register;
    }

    @OnClick({R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                toRegiste();
                break;
        }
    }

    @Override
    protected void preInitData() {

    }

    private void toRegiste() {
        final String account = et_account.getText().toString().trim();
        final String passwd = et_password.getText().toString().trim();
        final String passwdConfirm = et_confirm_password.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showSystemToast(mContext, "请输入账号");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showSystemToast(mContext, "请输入密码");
            return;
        }
        if (TextUtils.isEmpty(passwdConfirm)) {
            ToastUtils.showSystemToast(mContext, "请输入确认密码");
            return;
        }
        if (!passwdConfirm.equals(passwd)) {
            ToastUtils.showSystemToast(mContext, "密码输入不一致");
            return;
        }
        if(passwd.length()<6){
            ToastUtils.showSystemToast(mContext,"密码长度过短");
            return;
        }
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            ToastUtils.showSystemToast(mContext, "请检查网络连接");
            return;
        }
        tv_register.setEnabled(false);

    }




}
