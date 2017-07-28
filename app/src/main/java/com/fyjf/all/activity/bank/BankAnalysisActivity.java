package com.fyjf.all.activity.bank;

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
import com.fyjf.all.activity.AnalysisPDFActivity;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.report.ReportMsgActivity;
import com.fyjf.all.adapter.bank.BankAnalysisAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.BankAnalysis;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.report.AnalysisAllVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BankAnalysisActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, BankAnalysisAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<BankAnalysis> bankAnalysises;
    LinearLayoutManager layoutManager;
    BankAnalysisAdapter mAdapter;

    private int pageSize = 10;
    private int pageNo = 1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_analysis;
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
        bankAnalysises = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mAdapter = new BankAnalysisAdapter(mContext,bankAnalysises);
        mAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(mAdapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        mAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
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

    @Override
    public void openMsg(int position) {
        BankAnalysis info = bankAnalysises.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id",info.getId());
        startActivity(ReportMsgActivity.class,bundle);
    }

    @Override
    public void openItem(int position) {
        BankAnalysis info = bankAnalysises.get(position);
        Intent intent = new Intent(BankAnalysisActivity.this,AnalysisPDFActivity.class);
        intent.putExtra("bankAnalysis",info);
        startActivity(intent);
    }

    private void getData() {
        AnalysisAllVO vo = new AnalysisAllVO();
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(BankAnalysisActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
        resetRefreshView();
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code") == 0) {
                if(pageNo==1)bankAnalysises.clear();
                int size = bankAnalysises.size();
                bankAnalysises.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),BankAnalysis.class));
                mAdapter.notifyDataSetChanged();
                int addSize = bankAnalysises.size()-size;
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
        resetRefreshView();
    }

    private void resetRefreshView(){
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }
}
