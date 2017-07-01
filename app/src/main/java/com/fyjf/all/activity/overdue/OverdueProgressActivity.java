package com.fyjf.all.activity.overdue;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.OverdueProgressAdapter;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.widget.refreshview.XRefreshViewFooter;
import com.rey.material.widget.ImageView;


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
public class OverdueProgressActivity extends BaseActivity{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<OverdueProgress> overdueProgresses;
    LinearLayoutManager layoutManager;
    OverdueProgressAdapter overdueAdapter;


    private OverdueReport overdueReport;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_overdue_progress;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        overdueReport = (OverdueReport) getIntent().getSerializableExtra("overdueReport");
        recyclerView.setHasFixedSize(true);
        overdueProgresses = new ArrayList<>();
        if(overdueReport!=null&&overdueReport.getOverdueProgress()!=null){
            overdueProgresses.addAll(overdueReport.getOverdueProgress());
        }
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        overdueAdapter = new OverdueProgressAdapter(mContext,overdueProgresses);
//        overdueAdapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(overdueAdapter);

    }

}
