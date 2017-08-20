package com.fyjf.all.activity.overdue;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.common.ImageActivity;
import com.fyjf.all.adapter.AdvGridVerticalHorizonSpace;
import com.fyjf.all.adapter.ProgressImagesAdapter;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.vo.RequestUrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/7/26.
 */

public class ProgressImgsActivity extends BaseActivity implements ProgressImagesAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    OverdueProgress overdueProgress;
    private ProgressImagesAdapter adapter;
    private List<String> data;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_progress_imgs;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        overdueProgress = (OverdueProgress) getIntent().getSerializableExtra("overdueProgress");
        if(overdueProgress!=null&&!TextUtils.isEmpty(overdueProgress.getOverdueImgs())){
            data = Arrays.asList(overdueProgress.getOverdueImgs().split(","));
        }
        if(data==null)data = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);// 布局管理器。
        recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        recyclerView.addItemDecoration(new AdvGridVerticalHorizonSpace(0, 0));



        adapter = new ProgressImagesAdapter(mContext,data);
        adapter.setItemOperationListener(this);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openImgs(int position) {
        Intent intent = new Intent(mContext, ImageActivity.class);
        intent.putExtra("url", RequestUrl.file_image+data.get(position));
        mContext.startActivity(intent);
    }
}
