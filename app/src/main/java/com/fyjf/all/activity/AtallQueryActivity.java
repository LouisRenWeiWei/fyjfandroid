package com.fyjf.all.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.report.CreditReportActivity;
import com.fyjf.all.activity.report.ReportAnalysisActivity;
import com.fyjf.all.activity.report.ReportImagesActivity;
import com.fyjf.all.adapter.CustomerStateAdapter;
import com.fyjf.all.adapter.checkloan.CheckLoanCustomerAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.CustomerInfo;
import com.fyjf.dao.entity.CustomerState;
import com.fyjf.dao.entity.Page;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.loan.LoanCheckVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.fyjf.widget.spinner.NiceSpinner;
import com.rey.material.widget.ImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class AtallQueryActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,CheckLoanCustomerAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.et_customer_name)
    EditText et_customer_name;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_state)
    TextView tv_state;
    List<CustomerState> customerStates;
    CustomerStateAdapter customerStateAdapter;
    CustomerState customerState;

    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<CustomerInfo> customers;
    LinearLayoutManager layoutManager;
    CheckLoanCustomerAdapter customerAdapter;
    private Page page;



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
//        customerStates = new ArrayList<>();
//        customerStates.add(new CustomerState("贷后","1"));
//        customerStates.add(new CustomerState("预警","2"));
//        customerStates.add(new CustomerState("逾期","3"));
//        customerStateAdapter = new CustomerStateAdapter(mContext,customerStates);
//        spinner_state.setAdapter(customerStateAdapter);

        tv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        recyclerView.setHasFixedSize(true);
        customers = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        customerAdapter = new CheckLoanCustomerAdapter(mContext,customers);
        customerAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(customerAdapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        customerAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
//        xefreshView1.setPreLoadCount(4);
        //设置Recyclerview的滑动监听

        xRefreshView.setXRefreshViewListener(this);

        page = new Page();
    }

    private void showPopupWindow(View view) {
        Drawable up = getResources().getDrawable(R.mipmap.up);
        up.setBounds(0, 0, up.getMinimumWidth(), up.getMinimumHeight());
        tv_state.setCompoundDrawables(null,null,up,null);
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_select_state, null);
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        customerStates = new ArrayList<>();
        customerStates.add(new CustomerState("贷后","1"));
        customerStates.add(new CustomerState("预警","2"));
        customerStates.add(new CustomerState("逾期","3"));
        customerStateAdapter = new CustomerStateAdapter(mContext,customerStates);
        listView.setAdapter(customerStateAdapter);

        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customerState = customerStates.get(position);
                tv_state.setText(customerState.getName());
                popupWindow.dismiss();
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable( R.drawable.bg));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Drawable down = getResources().getDrawable(R.mipmap.down);
                down.setBounds(0, 0, down.getMinimumWidth(), down.getMinimumHeight());
                tv_state.setCompoundDrawables(null,null,down,null);
            }
        });
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRefresh(boolean isPullDown) {
        page.setPageNo(0);
        getData();
    }

    @Override
    public void onLoadMore(boolean isSilence) {
        getData();
    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }

    private void getData() {
        LoanCheckVO vo = new LoanCheckVO();
        vo.addParameter("page", JSONUtil.toJSONObject(page));
        int state = 1;
        if(customerState!=null){
            state = Integer.parseInt(customerState.getCode());
        }
        vo.addParameter("customerName", et_customer_name.getText().toString().trim());
        vo.addParameter("customerState", state);//贷后
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
            if (resp.getInt("code") == 0) {
                if(page.getPageNo()==0)customers.clear();
                int size = customers.size();
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customerAdapter.notifyDataSetChanged();
                int addSize = customers.size()-size;
                if(addSize>0&&addSize==page.getPageSize()){
                    xRefreshView.setPullLoadEnable(true);
                    page.setPageNo(page.getPageNo()+1);
                }else {
                    xRefreshView.setPullLoadEnable(false);
                }

            } else {
                ToastUtils.showSystemToast(mContext, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }

    @Override
    public void openReport(int position) {
        CustomerInfo customer = customers.get(position );
        if(customer!=null&&!TextUtils.isEmpty(customer.getReportId())){
            Bundle bundle = new Bundle();
            bundle.putString("reportId",customer.getReportId());
            startActivity(ReportActivity.class,bundle);
        }else {
            ToastUtils.showSystemToast(mContext,"客户暂未提交检查报告");
        }

    }

    @Override
    public void openCreditReport(int position) {
        CustomerInfo customer = customers.get(position );
        if(customer!=null&&!TextUtils.isEmpty(customer.getReportId())){
            Bundle bundle = new Bundle();
            bundle.putString("reportId",customer.getReportId());
            startActivity(CreditReportActivity.class,bundle);
        }else {
            ToastUtils.showSystemToast(mContext,"客户暂未提交检查报告");
        }
    }

    @Override
    public void openImageReport(int position) {
        CustomerInfo customer = customers.get(position );
        if(customer!=null&&!TextUtils.isEmpty(customer.getReportId())){
            Bundle bundle = new Bundle();
            bundle.putString("reportId",customer.getReportId());
            startActivity(ReportImagesActivity.class,bundle);
        }else {
            ToastUtils.showSystemToast(mContext,"客户暂未提交检查报告");
        }
    }

    @Override
    public void openAnalysisReport(int position) {
        CustomerInfo customer = customers.get(position );
        if(customer!=null){
            Bundle bundle = new Bundle();
            bundle.putString("customerId",customer.getId());
            startActivity(ReportAnalysisActivity.class,bundle);
        }else {
            ToastUtils.showSystemToast(mContext,"客户数据有误");
        }
    }
}
