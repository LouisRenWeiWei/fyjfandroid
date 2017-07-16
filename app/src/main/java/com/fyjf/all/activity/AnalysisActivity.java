package com.fyjf.all.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.fyjf.all.R;
import com.fyjf.all.adapter.checkloan.ReportAdapter;
import com.fyjf.dao.entity.AnalysisBean;
import com.fyjf.widget.refreshview.XRefreshView;

import java.util.List;

import butterknife.BindView;

public class AnalysisActivity extends BaseActivity implements XRefreshView.XRefreshViewListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<AnalysisBean> customers;
    LinearLayoutManager layoutManager;
    ReportAdapter customerAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_analysis;
    }

    @Override
    protected void preInitData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRefresh(boolean isPullDown) {

    }

    @Override
    public void onLoadMore(boolean isSilence) {

    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }
}
