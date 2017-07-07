package com.fyjf.all.activity.overdue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.CustomerOverdueAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.dao.entity.Page;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.overdue.OverduesVO;
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
* 同贷后检查
*/
public class OverdueActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,CustomerOverdueAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<OverdueReport> overdueReports;
    LinearLayoutManager layoutManager;
    CustomerOverdueAdapter overdueAdapter;

    private Page page;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_overdue;
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
        overdueReports = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        overdueAdapter = new CustomerOverdueAdapter(mContext,overdueReports);
        overdueAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(overdueAdapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        overdueAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
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
        OverduesVO vo = new OverduesVO();
        vo.addParameter("page", JSONUtil.toJSONObject(page));
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(OverdueActivity.this, "resp", "error");
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
                if(page.getPageNo()==0)overdueReports.clear();
                int size = overdueReports.size();
                overdueReports.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),OverdueReport.class));
                overdueAdapter.notifyDataSetChanged();
                int addSize = overdueReports.size()-size;
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
        OverdueReport item = overdueReports.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("pdfPath",item.getOverduePdf());
        startActivity(OverdueReportActivity.class,bundle);
    }

    @Override
    public void openProgress(int position) {
        OverdueReport item = overdueReports.get(position);
        Intent intent = new Intent(OverdueActivity.this,OverdueProgressActivity.class);
        intent.putExtra("overdueReport",item);
        startActivity(intent);
    }


}
