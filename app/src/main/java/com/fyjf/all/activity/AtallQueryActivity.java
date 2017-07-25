package com.fyjf.all.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.overdue.OverdueActivity;
import com.fyjf.all.activity.report.ReportDetailsActivity;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.MangerModel;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.query.MangerListVO;
import com.fyjf.widget.refreshview.utils.LogUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class AtallQueryActivity extends BaseActivity implements MaterialSpinner.OnItemSelectedListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.coustom_name)
    EditText coustom_name;
    @BindView(R.id.spinner_type)
    MaterialSpinner spinner_type;
    @BindView(R.id.spinner_manger)
    MaterialSpinner spinner_manger;
    @BindView(R.id.spinner_up_type)
    MaterialSpinner spinner_up_type;
    @BindView(R.id.time_prick)
    TextView time_prick;
    @BindView(R.id.btn_commit)
    Button btn_commit;

    String up_time;
    List<MangerModel> list;
    String custom_type = "1";
    String custom_manger;
    String update_type = "1";

    @Override
    protected int getContentLayout() {
        return R.layout.activity_atall_query;
    }

    @Override
    protected void preInitData() {
        list = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getManger();
        spinner_type.setItems("抵押贷款", "担保贷款", "信用贷款");

        spinner_up_type.setItems("贷后", "预警", "逾期");
        spinner_type.setOnItemSelectedListener(this);
        spinner_manger.setOnItemSelectedListener(this);
        spinner_up_type.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        if (view == spinner_type) {
            if (position == 0) {
                custom_type = "1";
            } else if (position == 1) {
                custom_type = "2";
            } else {
                custom_type = "3";
            }
            //            ToastUtils.showCustomerToast(mContext,position+":"+item);
        } else if (view == spinner_manger) {
            custom_manger = list.get(position).getId();
            //            ToastUtils.showCustomerToast(mContext,position+":"+item);
        } else if (view == spinner_up_type) {
            //            ToastUtils.showCustomerToast(mContext,position+":"+item);
            if (position == 0) {
                update_type = "1";
            } else if (position == 1) {
                update_type = "2";
            } else {
                update_type = "3";
            }
        }
    }

    @OnClick({R.id.time_prick, R.id.btn_commit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_prick:
                onYearMonthDayPicker(v);
                break;
            case R.id.btn_commit:
                String name = coustom_name.getText().toString().trim();
                //                if (name!=null && !name.equals("")){
                //                    if (up_time!=null && !up_time.equals("")){
                Intent intent = new Intent();
                if (!TextUtils.isEmpty(name)) {
                    intent.putExtra("customerName", name);
                }
                if (!TextUtils.isEmpty(up_time)) {
                    intent.putExtra("yearMonth", up_time.substring(0, 7));
                }
                intent.putExtra("loanType", custom_type);
                intent.putExtra("managerId", custom_manger);
                intent.putExtra("customerState", update_type);
                intent.setFlags(100);
                if (update_type.equals("1")) {
                    intent.putExtra("customerState",1);
                    intent.setClass(AtallQueryActivity.this, ReportDetailsActivity.class);
                } else if (update_type.equals("2")) {
                    intent.putExtra("customerState",2);
                    intent.setClass(AtallQueryActivity.this, ReportDetailsActivity.class);
                } else if (update_type.equals("3")) {
                    intent.setClass(AtallQueryActivity.this, OverdueActivity.class);
                }
                startActivity(intent);
                //                    }else {
                //                        ToastUtils.showSystemToast(mContext,"上报日期不能为空");
                //                    }
                //                }else {
                //                    ToastUtils.showSystemToast(mContext,"客户名称不能为空");
                //                }

                break;
        }
    }

    public void onYearMonthDayPicker(View view) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(1970, 1, 1);
        picker.setSelectedItem(year, month, day);
        //        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                up_time = year + "-" + month + "-" + day;
                time_prick.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    private void getManger() {
        MangerListVO vo = new MangerListVO();
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(AtallQueryActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            LogUtils.d("resp:" + resp);
            if (resp.getInt("code") == 0) {
                LogUtils.e("customers:" + resp.toString());
                list.clear();
                list.addAll(JSONUtil.toBeans(resp.getJSONArray("data"), MangerModel.class));
                custom_manger = list.get(0).getId();
                spinner_manger.setItems(list);
            } else {
                ToastUtils.showSystemToast(mContext, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
