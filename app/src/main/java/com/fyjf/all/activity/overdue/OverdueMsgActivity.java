package com.fyjf.all.activity.overdue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.OverdueMsgAdapter;
import com.fyjf.all.adapter.ReportMsgAdapter;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.OverdueMessageBean;
import com.fyjf.dao.entity.ReportMessageBean;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.overdue.OverdueMsgVO;
import com.fyjf.vo.report.ReportMsgVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.fyjf.widget.refreshview.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OverdueMsgActivity extends BaseActivity implements XRefreshView.XRefreshViewListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    OverdueMsgAdapter reportMsgAdapter;
    List<OverdueMessageBean> customers;
    LinearLayoutManager layoutManager;
    String user;
    String overdueId;

    private int pageSize = 10;
    private int pageNo = 1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_report_msg;
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
        //        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        reportMsgAdapter = new OverdueMsgAdapter(mContext,customers);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(reportMsgAdapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        reportMsgAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
        //        xefreshView1.setPreLoadCount(4);
        //设置Recyclerview的滑动监听

        xRefreshView.setXRefreshViewListener(this);
        Intent intent = getIntent();
        if (intent!=null){
            overdueId = intent.getStringExtra("overdueId");
            getData();
        }
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
        OverdueMsgVO vo = new OverdueMsgVO();
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("overdueId",overdueId);
        vo.request(OverdueMsgActivity.this, "resp", "error");
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
            LogUtils.d("resp:"+resp);
            if (resp.getInt("code") == 0) {
                if(pageNo==1)customers.clear();
                int size = customers.size();
                customers.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),ReportMessageBean.class));
                LogUtils.e("customers:"+customers.size());
                reportMsgAdapter.notifyDataSetChanged();
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
}
