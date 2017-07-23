package com.fyjf.all.activity.overdue;

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
import com.fyjf.all.activity.report.ReportMsgActivity;
import com.fyjf.all.adapter.CustomerOverdueAdapter;
import com.fyjf.all.app.AppData;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.overdue.OverduesVO;
import com.fyjf.widget.refreshview.XRefreshView;
import com.fyjf.widget.refreshview.XRefreshViewFooter;

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
* 逾期催收
*/
public class OverdueActivity extends BaseActivity implements XRefreshView.XRefreshViewListener ,CustomerOverdueAdapter.ItemOperationListener{
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<OverdueReport> overdueReports;
    LinearLayoutManager layoutManager;
    CustomerOverdueAdapter overdueAdapter;

    private int pageSize = 10;
    private int pageNo = 1;

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
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
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
        boolean fromSearch = intent.getBooleanExtra("fromSearch",false);
        if(fromSearch){
            getDataFromSearch(intent);
        }else {
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
        OverduesVO vo = new OverduesVO();
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("pageNo",pageNo);
        String customerName = search_et.getText().toString().trim();
        if(!TextUtils.isEmpty(customerName))vo.addParameter("customerName",customerName);
        vo.addParameter("account", AppData.getString(AppData.ACCOUNT));
        vo.request(OverdueActivity.this, "resp", "error");
    }

    private void getDataFromSearch(Intent intent) {
        OverduesVO vo = new OverduesVO();
        vo.addParameter("pageSize",pageSize);
        vo.addParameter("pageNo",pageNo);
        vo.addParameter("account",AppData.getString(AppData.ACCOUNT));
        //下面这个参数是贷后和逾期的参数
        //vo.addParameter("customerState",intent.getStringExtra("customerState"));//上报类型　　 1：贷后  2：预警 3： 逾期
        vo.addParameter("customerName",intent.getStringExtra("customerName"));
        vo.addParameter("yearMonth",intent.getStringExtra("yearMonth"));//查询时间
        vo.addParameter("loanType",intent.getStringExtra("loanType"));//客户类型：　1:抵押贷款 2:担保贷款 3:信用贷款
        vo.addParameter("managerId",intent.getStringExtra("managerId"));//客户经理id
        vo.request(OverdueActivity.this, "resp", "error");
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
                if(pageNo == 1)overdueReports.clear();
                int size = overdueReports.size();
                overdueReports.addAll(JSONUtil.toBeans(resp.getJSONArray("data"),OverdueReport.class));
//                back.setText(TimeUtil.timeHao2Date(overdueReports.get(0).getOverdueStart(),"MM")+"月");
                overdueAdapter.notifyDataSetChanged();
                int addSize = overdueReports.size()-size;
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

    @Override
    public void openMsg(int position) {
        OverdueReport item = overdueReports.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id",item.getOverdueId());
        startActivity(ReportMsgActivity.class,bundle);
    }

    @Override
    public void openReport(int position) {
        OverdueReport item = overdueReports.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("pdfPath",item.getOverduePDF());
        startActivity(OverdueReportActivity.class,bundle);
    }

    @Override
    public void openProgress(int position) {
        OverdueReport item = overdueReports.get(position);
        Intent intent = new Intent(OverdueActivity.this,OverdueProgressActivity.class);
        intent.putExtra("id",item.getOverdueId());
        intent.putExtra("day",item.getOverdueDays());
        startActivity(intent);
    }


}
