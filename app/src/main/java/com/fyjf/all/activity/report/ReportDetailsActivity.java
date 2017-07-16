package com.fyjf.all.activity.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.ReportPDFActivity;
import com.fyjf.all.adapter.ReportDetailsAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.CustomerInfo;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.report.ReportDetailsVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.fyjf.widget.refreshview.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReportDetailsActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, ReportDetailsAdapter.ItemOperationListener {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<CustomerInfo> customers;
    LinearLayoutManager layoutManager;
    ReportDetailsAdapter customerAdapter;
    String yearTime;

    private int pageSize = 10;
    private int pageNo = 1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_report_details;
    }

    @Override
    protected void preInitData() {
        Intent intent = getIntent();
        if (intent!=null){
            yearTime = intent.getStringExtra("time");
            back.setText(yearTime.substring(4,6));
        }
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
        customerAdapter = new ReportDetailsAdapter(mContext,customers);
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

        getData();
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
        vo.addParameter("customerState", 1);//贷后
        vo.addParameter("yearMonth",yearTime);
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(ReportDetailsActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            LogUtils.d("resp:"+resp);
            if (resp.getInt("code") == 0) {
                if(pageNo==1)customers.clear();
                int size = customers.size();
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),CustomerInfo.class));
                LogUtils.e("customers:"+customers.size());
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
        CustomerInfo info = customers.get(position);
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
        CustomerInfo info = customers.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id",info.getId());
        startActivity(ReportImagesActivity.class,bundle);
    }

    /**
     * 检查报告
     * @param position
     */
    @Override
    public void openReport(int position) {
        CustomerInfo info = customers.get(position);
        startActivity(ReportPDFActivity.class);
    }

    /**
     * 量化分析
     * @param position
     */
    @Override
    public void openQuantified(int position) {
        CustomerInfo info = customers.get(position);
        startActivity(ReportAnalysisActivity.class);
    }

    /**
     * 征信报告
     * @param position
     */
    @Override
    public void openCredit(int position) {
        CustomerInfo info = customers.get(position);
        startActivity(CreditReportActivity.class);
    }
}
