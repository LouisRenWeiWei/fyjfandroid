package com.fyjf.all.activity.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.ReportFirstPageDetailsAdapter;
import com.fyjf.all.adapter.checkloan.ReportFirstPageAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.push.PushConstants;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.CustomerReportInfo;
import com.fyjf.dao.entity.LoanTime;
import com.fyjf.utils.JSONUtil;
import com.fyjf.utils.TimeUtils;
import com.fyjf.vo.report.ReportDetailsVO;
import com.fyjf.vo.report.ReportListVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XScrollView;
import com.fyjf.widget.refreshview.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportMainActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, ReportFirstPageAdapter.ItemOperationListener, ReportFirstPageDetailsAdapter.ItemOperationListener {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.search_et)
    EditText search_et;

    @BindView(R.id.xScrollView)
    XScrollView xScrollView;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;

    @BindView(R.id.recyclerViewLatest)
    RecyclerView recyclerViewLatest;
    List<CustomerReportInfo> customerReportInfosLatest;
    ReportFirstPageDetailsAdapter customerReportInfosLatestAdapter;
    @BindView(R.id.ll_load_more_latest)
    LinearLayout ll_load_more_latest;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<LoanTime> customers;
    ReportFirstPageAdapter customerAdapter;


    private int pageSize = 10;
    private int pageNo = 1;
    private int pageLatestNo = 1;
    private Calendar currentYearMonth;
    private boolean canRequstNext = true;
    private int tryCount = 0;

    private int customerState = 1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_loan_check;
    }

    @Override
    protected void preInitData() {
        customerState = getIntent().getIntExtra("customerState", 1);
        if (1 == customerState) {
            back.setText("贷后检查");
        } else {
            back.setText("预警信息");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    String name = search_et.getText().toString().trim();
                    if(!TextUtils.isEmpty(name)){
                        Intent intent = new Intent(mContext, ReportListActivity.class);
                        intent.setFlags(100);
                        intent.putExtra("customerName", name);
                        //intent.putExtra("fromSearch",true);
                        intent.putExtra("customerState",1);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });


        recyclerViewLatest.setHasFixedSize(true);
        customerReportInfosLatest = new ArrayList<>();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerViewLatest.setLayoutManager(layoutManager2);
        customerReportInfosLatestAdapter = new ReportFirstPageDetailsAdapter(mContext, customerReportInfosLatest, customerState);
        customerReportInfosLatestAdapter.setItemOperationListener(this);
        recyclerViewLatest.setAdapter(customerReportInfosLatestAdapter);


        recyclerView.setHasFixedSize(true);
        customers = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        customerAdapter = new ReportFirstPageAdapter(mContext, customers, customerState);
        customerAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(customerAdapter);
//        customerAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(false);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
//        xefreshView1.setPreLoadCount(4);
        //设置Recyclerview的滑动监听

        xRefreshView.setXRefreshViewListener(this);

        ll_load_more_latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLatestData();//加载最近更多
            }
        });

        getLatestData();
    }

    @Override
    public void onRefresh() {
        Log.d("", "");
    }

    @Override
    public void onRefresh(boolean isPullDown) {
        pageNo = 1;
        pageLatestNo = 1;
        currentYearMonth = null;
        canRequstNext = true;
        tryCount = 0;
        getLatestData();
    }

    @Override
    public void onLoadMore(boolean isSilence) {
        getData();
    }

    @Override
    public void onRelease(float direction) {
        Log.d("", "");
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {
        Log.d("", "");
    }

    private void getLatestData() {
        if (currentYearMonth == null) {
            currentYearMonth = Calendar.getInstance();
            Date date = new Date();
            currentYearMonth.setTime(date);
        }
        ReportDetailsVO vo = new ReportDetailsVO();
        vo.addParameter("pageNo", pageLatestNo);
        vo.addParameter("pageSize", pageSize);
        vo.addParameter("customerState", customerState);//贷后
        vo.addParameter("yearMonth", TimeUtils.formateDate(currentYearMonth.getTime(), "yyyyMM"));
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(ReportMainActivity.this, "respLatest", "errorLatest");
    }

    @ResponseError(name = "errorLatest")
    void errorLatest(VolleyError error) {
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }

    @ResponseSuccess(name = "respLatest")
    void respLatest(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code") == 0) {
                if (pageLatestNo == 1) customerReportInfosLatest.clear();
                int size = customerReportInfosLatest.size();
                customerReportInfosLatest.addAll(JSONUtil.toBeans(resp.getJSONArray("data"), CustomerReportInfo.class));
                customerReportInfosLatestAdapter.notifyDataSetChanged();
                int addSize = customerReportInfosLatest.size() - size;
                if (addSize > 0) {
                    if (canRequstNext) {
                        getData();
                        canRequstNext = false;
                    }
                    if (addSize < pageSize) {
                        ll_load_more_latest.setVisibility(View.GONE);
                    } else {
                        ll_load_more_latest.setVisibility(View.VISIBLE);
                        pageLatestNo++;
                    }

                } else {
                    currentYearMonth.add(Calendar.MONTH, -1);
                    if (tryCount < 5) {
                        getLatestData();//继续请求
                        tryCount++;
                    } else {
                        getData();
                    }
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

    private void getData() {
        ReportListVO vo = new ReportListVO();
//        vo.addParameter("page", JSONUtil.toJSONObject(page));
        vo.addParameter("pageNo", pageNo);
        vo.addParameter("pageSize", pageSize);
        vo.addParameter("customerState", customerState);//贷后
        vo.addParameter("yearMonth", TimeUtils.formateDate(currentYearMonth.getTime(), "yyyyMM"));//
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(ReportMainActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
        try {
            JSONObject resp = new JSONObject(response);
            LogUtils.d("resp:" + resp);
            if (resp.getInt("code") == 0) {
                if (pageNo == 1) customers.clear();
                int size = customers.size();
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"), LoanTime.class));
                LogUtils.e("customers:" + customers.size());
                customerAdapter.notifyDataSetChanged();
                int addSize = customers.size() - size;
                if (addSize > 0 && addSize == pageSize) {
                    xRefreshView.setPullLoadEnable(true);
                    pageNo++;
                } else {
                    xRefreshView.setPullLoadEnable(false);

                }

            } else {
                ToastUtils.showSystemToast(mContext, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    @Override
    public void openMonthReport(int position) {
        LoanTime time = customers.get(position);
        Intent intent = new Intent();
        intent.putExtra("time", time.getYearMonth());
        intent.putExtra("customerState", customerState);
        intent.setClass(ReportMainActivity.this, ReportListActivity.class);
        startActivity(intent);
    }

    @Override
    public void openMsg(int position) {
        CustomerReportInfo info = customerReportInfosLatest.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", info.getId());
        startActivity(ReportMsgActivity.class, bundle);
    }

    @Override
    public void openImg(int position) {
        CustomerReportInfo info = customerReportInfosLatest.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", info.getId());
        startActivity(ReportImagesActivity.class, bundle);
    }

    @Override
    public void openPDF(int position) {
        CustomerReportInfo info = customerReportInfosLatest.get(position);
        Intent intent = new Intent();
        intent.putExtra("reportId", info.getId());
        intent.setClass(ReportMainActivity.this, ReportPDFActivity.class);
        startActivity(intent);
    }

    @Override
    public void openQuantified(int position) {
        CustomerReportInfo info = customerReportInfosLatest.get(position);
        Intent intent = new Intent();
        intent.putExtra("reportId", info.getId());
        intent.setClass(ReportMainActivity.this, ReportEchartsActivity.class);
        startActivity(intent);
    }

    @Override
    public void openCredit(int position) {
        CustomerReportInfo info = customerReportInfosLatest.get(position);
        Intent intent = new Intent();
        intent.putExtra("report", info);
        intent.setClass(ReportMainActivity.this, CreditPDFActivity.class);
        startActivity(intent);
    }


}
