package com.fyjf.all.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.utils.ToastUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;

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

    @Override
    protected int getContentLayout() {
        return R.layout.activity_atall_query;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner_type.setItems("客户类型1", "客户类型2", "客户类型3", "客户类型4", "客户类型5");
        spinner_manger.setItems("客户经理1", "客户经理2", "客户经理3", "客户经理4", "客户经理5");
        spinner_up_type.setItems("上报类型1", "上报类型2", "上报类型3", "上报类型4", "上报类型5");
        spinner_type.setOnItemSelectedListener(this);
        spinner_manger.setOnItemSelectedListener(this);
        spinner_up_type.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        if (view==spinner_type){
            ToastUtils.showCustomerToast(mContext,position+":"+item);
        } else if (view==spinner_manger){
            ToastUtils.showCustomerToast(mContext,position+":"+item);
        } else if (view==spinner_up_type){
            ToastUtils.showCustomerToast(mContext,position+":"+item);
        }
    }

    @OnClick({R.id.time_prick,R.id.btn_commit})
    void onClick(View v){
        switch (v.getId()){
            case R.id.time_prick:
                onYearMonthDayPicker(v);
                break;
            case R.id.btn_commit:

                break;
        }
    }

    public void onYearMonthDayPicker(View view) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
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
//                showToast(year + "-" + month + "-" + day);
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
}
