package com.fyjf.all.activity.user;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.LoginActivity;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.vo.user.ChangePasswd;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdatePwdActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.old_edit)
    EditText old_edit;
    @BindView(R.id.new_edit)
    EditText new_edit;
    @BindView(R.id.confirm_edit)
    EditText confirm_edit;
    @BindView(R.id.btn_confirm)
    TextView btn_confirm;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void preInitData() {
        old_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>=6){
                    new_edit.setEnabled(true);
                }else {
                    new_edit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        new_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>=6){
                    confirm_edit.setEnabled(true);
                }else {
                    confirm_edit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirm_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void toConfirmModify() {
        String oldPwd = old_edit.getText().toString().trim();
        String newPwd = new_edit.getText().toString().trim();
        String confirmPwd = confirm_edit.getText().toString().trim();
        if(oldPwd.equals(newPwd)){
            ToastUtils.showSystemToast(mContext,"新密码不能与原密码相同");
            return;
        }else if(!newPwd.equals(confirmPwd)){
            ToastUtils.showSystemToast(mContext,"确认密码和新密码输入不一致");
            return;
        }else {
            submitData(oldPwd,newPwd);
        }
    }

    private void submitData(String oldPwd, String newPwd) {
        ChangePasswd vo = new ChangePasswd();
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.addParameter("oldPasswd", oldPwd);
        vo.addParameter("newPasswd", newPwd);
        vo.request(UpdatePwdActivity.this, "resp", "error");
    }



    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "修改密码失败");
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code") == 0) {
                ToastUtils.showSystemToast(mContext, "修改密码成功");
                AppData.saveString(AppData.ACCOUNT,"");
                AppData.saveString(AppData.PASSWORD,"");
                startActivity(LoginActivity.class);
            } else {
                ToastUtils.showSystemToast(mContext, "修改密码失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.back,R.id.btn_confirm})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_confirm:
                toConfirmModify();
                break;
        }
    }
}
