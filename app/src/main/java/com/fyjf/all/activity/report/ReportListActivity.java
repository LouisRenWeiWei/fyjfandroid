package com.fyjf.all.activity.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.ReportDetailsAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.CustomerReportInfo;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.report.ReportDetailsVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReportListActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, ReportDetailsAdapter.ItemOperationListener {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<CustomerReportInfo> customers;
    LinearLayoutManager layoutManager;
    ReportDetailsAdapter customerAdapter;
    String yearTime;

    private int pageSize = 10;
    private int pageNo = 1;

    private int customerState =1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_report_details;
    }

    @Override
    protected void preInitData() {
        customerState = getIntent().getIntExtra("customerState",1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        customers = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        customerAdapter = new ReportDetailsAdapter(mContext,customers,customerState);
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
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    pageNo = 1;
                    getData();
                    return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            if (intent.getFlags()!=100){
                yearTime = intent.getStringExtra("time");
                back.setText(yearTime.substring(4,6));
                getData();
            }else {
                if(customerState==1){
                    back.setText("贷后检查");
                }else {
                    back.setText("预警信息");
                }
                getQuery(intent);
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        setEvent();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRefresh(boolean isPullDown) {
        pageNo = 1;
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
        ReportDetailsVO vo = new ReportDetailsVO();
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("customerState", customerState);//贷后
        vo.addParameter("yearMonth",yearTime);
        String customerName = search_et.getText().toString().trim();
        if(!TextUtils.isEmpty(customerName))vo.addParameter("customerName",customerName);
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(ReportListActivity.this, "resp", "error");
    }

    private void getQuery(Intent intent){
        ReportDetailsVO vo = new ReportDetailsVO();
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        //下面这个参数是贷后和逾期的参数
        if (!TextUtils.isEmpty(intent.getStringExtra("customerName"))){
            String name = intent.getStringExtra("customerName");
            vo.addParameter("customerName",name);
            search_et.setText(name);
        }
        if (!TextUtils.isEmpty(intent.getStringExtra("yearMonth"))){
            vo.addParameter("yearMonth",intent.getStringExtra("yearMonth"));
        }
        vo.addParameter("customerState",intent.getIntExtra("customerState",1));//上报类型　　 1：贷后  2：预警 3： 逾期
        vo.addParameter("loanType",intent.getStringExtra("loanType"));//客户类型：　1:抵押贷款 2:担保贷款 3:信用贷款
        vo.addParameter("managerId",intent.getStringExtra("managerId"));//客户经理id
        vo.request(ReportListActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code") == 0) {
                if(pageNo==1)customers.clear();
                int size = customers.size();
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerReportInfo.class));
                customerAdapter.notifyDataSetChanged();
                int addSize = customers.size()-size;
                if(addSize>0&&addSize==pageSize){
                    xRefreshView.setPullLoadEnable(true);
                    pageNo++;
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

    /**
     * 消息
     * @param position
     */
    @Override
    public void openMsg(int position) {
        CustomerReportInfo info = customers.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id",info.getId());
        startActivity(ReportMsgActivity.class,bundle);
    }

    /**
     * 图片
     * @param position
     */
    @Override
    public void openImg(int position) {
        CustomerReportInfo info = customers.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id",info.getId());
        startActivity(ReportImagesActivity.class,bundle);
    }

    /**
     * 检查报告
     * @param position
     */
    @Override
    public void openPDF(int position) {
        CustomerReportInfo info = customers.get(position);
        Intent intent = new Intent();
        intent.putExtra("reportId",info.getId());
        intent.setClass(ReportListActivity.this,ReportPDFActivity.class);
        startActivity(intent);
    }

    /**
     * 量化分析
     * @param position
     */
    @Override
    public void openQuantified(int position) {
        CustomerReportInfo info = customers.get(position);
        Intent intent = new Intent();
        intent.putExtra("reportId",info.getId());
        intent.setClass(ReportListActivity.this,ReportEchartsActivity.class);
        startActivity(intent);

    }

    /**
     * 征信报告
     * @param position
     */
    @Override
    public void openCredit(int position) {
        CustomerReportInfo info = customers.get(position);
        Intent intent = new Intent();
        intent.putExtra("report",info);
        intent.setClass(ReportListActivity.this,CreditPDFActivity.class);
        startActivity(intent);
    }

}