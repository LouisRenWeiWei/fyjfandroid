package com.fyjf.all.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.MainActivity;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.utils.NetworkUtils;
import com.fyjf.vo.user.LoginVO;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ASUS on 2017/5/17.
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_login)
    Button tv_login;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_login;
    }

    @OnClick({R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        final String account = et_account.getText().toString().trim();
        final String passwd = et_password.getText().toString().trim();
        if(TextUtils.isEmpty(account)){
            ToastUtils.showSystemToast(mContext,"请输入账号");
            return;
        }
        if(TextUtils.isEmpty(passwd)){
            ToastUtils.showSystemToast(mContext,"请输入密码");
            return;
        }
        if(!NetworkUtils.isNetworkAvailable(mContext)){
            ToastUtils.showSystemToast(mContext,"请检查网络连接");
            return;
        }
        tv_login.setEnabled(false);
        doLogin(account,passwd);
    }

    private void doLogin(String account, String passwd) {
        LoginVO vo = new LoginVO();
        vo.addParameter("account", account);
        vo.addParameter("passwd", passwd);
        vo.request(LoginFragment.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "登录失败");
        tv_login.setEnabled(true);
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            tv_login.setEnabled(true);
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code") == 0) {
                AppData.saveString(AppData.ACCOUNT,et_account.getText().toString().trim());
                AppData.saveString(AppData.PASSWORD,et_password.getText().toString().trim());
                startActivity(MainActivity.class);
                getActivity().finish();
            } else {
                ToastUtils.showSystemToast(mContext, "用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void preInitData() {
        et_account.setText(AppData.getString(AppData.ACCOUNT));
        et_password.setText(AppData.getString(AppData.PASSWORD));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
