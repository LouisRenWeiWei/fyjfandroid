package com.fyjf.all.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.report.CreditReportActivity;
import com.fyjf.all.activity.report.ReportAnalysisActivity;
import com.fyjf.all.activity.report.ReportImagesActivity;
import com.fyjf.all.adapter.checkloan.ReportAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.CustomerInfo;
import com.fyjf.dao.entity.Page;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.loan.LoanCheckVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.rey.material.widget.ImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,ReportAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<CustomerInfo> customers;
    LinearLayoutManager layoutManager;
    ReportAdapter customerAdapter;

    private Page page;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_loan_check;
    }

    @Override
    protected void preInitData() {
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
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        customerAdapter = new ReportAdapter(mContext,customers);
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
        getData();
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
        vo.addParameter("customerState", 1);//贷后
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(ReportActivity.this, "resp", "error");
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
            startActivity(ReportPDFActivity.class,bundle);
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
