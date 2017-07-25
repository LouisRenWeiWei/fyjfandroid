//package com.fyjf.all.activity;
//
//import android.content.Intent;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.android.volley.VolleyError;
//import com.android.volley.ext.ResponseError;
//import com.android.volley.ext.ResponseSuccess;
//import com.fyjf.all.R;
//import com.fyjf.all.activity.waring.WaringDetailsActivity;
//import com.fyjf.all.adapter.checkloan.ReportFirstPageAdapter;
//import com.fyjf.all.app.AppData;
//import com.fyjf.all.utils.ToastUtils;
//import com.fyjf.dao.entity.LoanTime;
//import com.fyjf.utils.JSONUtil;
//import com.fyjf.vo.report.ReportListVO;
//import com.fyjf.widget.refreshview.XRefreshView;
//import com.fyjf.widget.refreshview.XRefreshViewFooter;
//import com.fyjf.widget.refreshview.utils.LogUtils;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//
///**
// * Created by ASUS on 2017/6/23.
// */
///*
//* author: renweiwei
//* datetime:
//* 同贷后检查
//*/
//public class WaringActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,ReportFirstPageAdapter.ItemOperationListener
//{
//    @BindView(R.id.back)
//    TextView back;
//    @BindView(R.id.xRefreshView)
//    XRefreshView xRefreshView;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    List<LoanTime> customers;
//    LinearLayoutManager layoutManager;
//    ReportFirstPageAdapter customerAdapter;
//
//    private int pageSize = 10;
//    private int pageNo = 1;
//
//    @Override
//    protected int getContentLayout() {
//        return R.layout.activity_waring;
//    }
//
//    @Override
//    protected void preInitData() {
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });
//
//        recyclerView.setHasFixedSize(true);
//        customers = new ArrayList<>();
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        //        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
//        customerAdapter = new ReportFirstPageAdapter(mContext,customers);
//        customerAdapter.setItemOperationListener(this);
//        // 静默加载模式不能设置footerview
//        recyclerView.setAdapter(customerAdapter);
//
//        //设置刷新完成以后，headerview固定的时间
//        xRefreshView.setPinnedTime(1000);
//        xRefreshView.setMoveForHorizontal(true);
//        xRefreshView.setPullLoadEnable(true);
//        xRefreshView.setAutoLoadMore(false);
//        customerAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
//        xRefreshView.enableReleaseToLoadMore(true);
//        xRefreshView.enableRecyclerViewPullUp(true);
//        xRefreshView.enablePullUpWhenLoadCompleted(true);
//        //设置静默加载时提前加载的item个数
//        //        xefreshView1.setPreLoadCount(4);
//        //设置Recyclerview的滑动监听
//
//        xRefreshView.setXRefreshViewListener(this);
//
//        getData();
//    }
//
//    @Override
//    public void onRefresh() {
//
//    }
//
//    @Override
//    public void onRefresh(boolean isPullDown) {
//        pageNo = 1;
//        getData();
//    }
//
//    @Override
//    public void onLoadMore(boolean isSilence) {
//        getData();
//    }
//
//    @Override
//    public void onRelease(float direction) {
//
//    }
//
//    @Override
//    public void onHeaderMove(double headerMovePercent, int offsetY) {
//
//    }
//
//    private void getData() {
//        ReportListVO vo = new ReportListVO();
//        vo.addParameter("pageNo",pageNo);
//        vo.addParameter("pageSize",pageSize);
//        vo.addParameter("customerState", 2);//
//        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
//        vo.request(WaringActivity.this, "resp", "error");
//    }
//
//    @ResponseError(name = "error")
//    void error(VolleyError error) {
//        ToastUtils.showSystemToast(mContext, "请求失败");
//        xRefreshView.stopRefresh();
//        xRefreshView.stopLoadMore();
//    }
//
//    @ResponseSuccess(name = "resp")
//    void resp(String response) {
//        try {
//            JSONObject resp = new JSONObject(response);
//            LogUtils.d("resp:"+resp);
//            if (resp.getInt("code") == 0) {
//                if(pageNo==1)customers.clear();
//                int size = customers.size();
//                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),LoanTime.class));
//                LogUtils.e("customers:"+customers.size());
//                customerAdapter.notifyDataSetChanged();
//                int addSize = customers.size()-size;
//                if(addSize>0&&addSize==pageSize){
//                    xRefreshView.setPullLoadEnable(true);
//                    pageNo++;
//                }else {
//                    xRefreshView.setPullLoadEnable(false);
//                }
//
//            } else {
//                ToastUtils.showSystemToast(mContext, "");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        xRefreshView.stopRefresh();
//        xRefreshView.stopLoadMore();
//    }
//
//
//    @Override
//    public void openMonthReport(int position) {
//        LoanTime time = customers.get(position);
//        Intent intent = new Intent();
//        intent.putExtra("time",time.getYearMonth());
//        intent.setClass(WaringActivity.this, WaringDetailsActivity.class);
//        startActivity(intent);
//    }
//}
