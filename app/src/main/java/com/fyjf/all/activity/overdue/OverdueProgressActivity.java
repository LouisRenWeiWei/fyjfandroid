package com.fyjf.all.activity.overdue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.ImageActivity;
import com.fyjf.all.activity.report.ReportMsgActivity;
import com.fyjf.all.adapter.OverdueProgressAdapter;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.report.ReportProgressVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.fyjf.widget.refreshview.utils.LogUtils;

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
public class OverdueProgressActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,OverdueProgressAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.collection_title)
    TextView collection_title;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<OverdueProgress> overdueProgresses;
    LinearLayoutManager layoutManager;
    OverdueProgressAdapter overdueAdapter;

    private int pageSize = 100;
    private int pageNo = 1;
    private String reportId;
    private int day;
    private double money;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_overdue_progress;
    }

    @Override
    protected void preInitData() {
        Intent intent = getIntent();
        if(intent!=null){
            reportId = intent.getStringExtra("id");
            day = intent.getIntExtra("day",0);
            //collection_title.setText("进入催收第"+day+"天，累计收回"+money+"万元");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        overdueProgresses = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        overdueAdapter = new OverdueProgressAdapter(mContext,overdueProgresses);
        overdueAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(overdueAdapter);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setAutoLoadMore(false);
        overdueAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
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
        ReportProgressVO vo = new ReportProgressVO();
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("overdueId",reportId);
        vo.request(OverdueProgressActivity.this, "resp", "error");
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
                if(pageNo==1)overdueProgresses.clear();
                overdueProgresses.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),OverdueProgress.class));

                overdueAdapter.notifyDataSetChanged();

                money = 0;
                for(OverdueProgress item : overdueProgresses){
                    try {
                        money += Double.parseDouble(item.getMoney());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                collection_title.setText("进入催收第"+day+"天，累计收回"+money+"万元");
            } else {
                ToastUtils.showSystemToast(mContext, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }

//    @Override
//    public void openPDF(int position) {
//        OverdueProgress item = overdueProgresses.get(position);
//        Bundle bundle = new Bundle();
//        bundle.putString("id",item.getOverdueId());
//        startActivity(OverdueProgressDetailsActivity.class,bundle);
//    }


    @Override
    public void openMsg(int position) {
        OverdueProgress item = overdueProgresses.get(position);
        Intent intent = new Intent(mContext,OverdueMsgActivity.class);
        intent.putExtra("overdueId",item.getOverdueId());
        startActivity(intent);
    }
}
