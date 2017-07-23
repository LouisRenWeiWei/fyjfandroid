package com.fyjf.all.activity.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.ImageActivity;
import com.fyjf.all.adapter.checkloan.ReportImagAdapter;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.ImageFile;
import com.fyjf.dao.entity.ReportImageBean;
import com.fyjf.utils.JSONUtil;
import com.fyjf.vo.RequestUrl;
import com.fyjf.vo.report.ReportImageVO;

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
*
*/
public class ReportImagesActivity extends BaseActivity implements ReportImagAdapter.ItemOperationListener{
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.recyclerView_reportFinance)
    RecyclerView recyclerView_reportFinance;
    List<ImageFile> imgs_reportFinance;
    ReportImagAdapter adapter_reportFinance;

    @BindView(R.id.recyclerView_reportBusinessManage)
    RecyclerView recyclerView_reportBusinessManage;
    List<ImageFile> imgs_reportBusinessManage;
    ReportImagAdapter adapter_reportBusinessManage;

    @BindView(R.id.recyclerView_reportCumtomerQuality)
    RecyclerView recyclerView_reportCumtomerQuality;
    List<ImageFile> imgs_reportCumtomerQuality;
    ReportImagAdapter adapter_reportCumtomerQuality;

    @BindView(R.id.recyclerView_reportCustomerFinancial)
    RecyclerView recyclerView_reportCustomerFinancial;
    List<ImageFile> imgs_reportCustomerFinancial;
    ReportImagAdapter adapter_reportCustomerFinancial;

    @BindView(R.id.recyclerView_reportGuarantee)
    RecyclerView recyclerView_reportGuarantee;
    List<ImageFile> imgs_reportGuarantee;
    ReportImagAdapter adapter_reportGuarantee;

    private String reportId;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_report_images;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        recyclerView_reportFinance.setHasFixedSize(true);
        imgs_reportFinance = new ArrayList<>();
        LinearLayoutManager layoutManager_reportFinance = new LinearLayoutManager(this);
        layoutManager_reportFinance.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_reportFinance.setLayoutManager(layoutManager_reportFinance);
        adapter_reportFinance = new ReportImagAdapter(mContext,imgs_reportFinance);
        adapter_reportFinance.setItemOperationListener(this);
        recyclerView_reportFinance.setAdapter(adapter_reportFinance);

        recyclerView_reportBusinessManage.setHasFixedSize(true);
        imgs_reportBusinessManage = new ArrayList<>();
        LinearLayoutManager layoutManager_reportBusinessManage = new LinearLayoutManager(this);
        layoutManager_reportBusinessManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_reportBusinessManage.setLayoutManager(layoutManager_reportBusinessManage);
        adapter_reportBusinessManage = new ReportImagAdapter(mContext,imgs_reportBusinessManage);
        adapter_reportBusinessManage.setItemOperationListener(this);
        recyclerView_reportBusinessManage.setAdapter(adapter_reportBusinessManage);

        recyclerView_reportCumtomerQuality.setHasFixedSize(true);
        imgs_reportCumtomerQuality = new ArrayList<>();
        LinearLayoutManager layoutManager_reportCumtomerQuality = new LinearLayoutManager(this);
        layoutManager_reportCumtomerQuality.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_reportCumtomerQuality.setLayoutManager(layoutManager_reportCumtomerQuality);
        adapter_reportCumtomerQuality = new ReportImagAdapter(mContext,imgs_reportCumtomerQuality);
        adapter_reportCumtomerQuality.setItemOperationListener(this);
        recyclerView_reportCumtomerQuality.setAdapter(adapter_reportCumtomerQuality);


        recyclerView_reportCustomerFinancial.setHasFixedSize(true);
        imgs_reportCustomerFinancial = new ArrayList<>();
        LinearLayoutManager layoutManager_reportCustomerFinancial = new LinearLayoutManager(this);
        layoutManager_reportCustomerFinancial.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_reportCustomerFinancial.setLayoutManager(layoutManager_reportCustomerFinancial);
        adapter_reportCustomerFinancial = new ReportImagAdapter(mContext,imgs_reportCustomerFinancial);
        adapter_reportCustomerFinancial.setItemOperationListener(this);
        recyclerView_reportCustomerFinancial.setAdapter(adapter_reportCustomerFinancial);


        recyclerView_reportGuarantee.setHasFixedSize(true);
        imgs_reportGuarantee = new ArrayList<>();
        LinearLayoutManager layoutManager_reportGuarantee = new LinearLayoutManager(this);
        layoutManager_reportGuarantee.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_reportGuarantee.setLayoutManager(layoutManager_reportGuarantee);
        adapter_reportGuarantee = new ReportImagAdapter(mContext,imgs_reportGuarantee);
        adapter_reportGuarantee.setItemOperationListener(this);
        recyclerView_reportGuarantee.setAdapter(adapter_reportGuarantee);

        Intent intent = getIntent();
        if (intent!=null){
            Bundle bundle = intent.getExtras();
            reportId = bundle.getString("id");
        }
//        reportId = getIntent().getStringExtra("reportId");
        if(!TextUtils.isEmpty(reportId)){
            getData();
        }
    }

    private void getData() {
        ReportImageVO vo = new ReportImageVO();
        vo.addParameter("reportId", reportId);
        vo.request(ReportImagesActivity.this, "resp", "error");
    }

    @ResponseError(name = "error")
    void error(VolleyError error) {
        ToastUtils.showSystemToast(mContext, "请求失败");
    }

    @ResponseSuccess(name = "resp")
    void resp(String response) {
        try {
            JSONObject resp = new JSONObject(response);
            if (resp.getInt("code")==0){
                ReportImageBean reportImage = JSONUtil.toBean(resp.getJSONObject("data"),ReportImageBean.class);
                if(reportImage!=null&&!TextUtils.isEmpty(reportImage.getCustomerFinancialImgs())){
                    String[] imgs = reportImage.getCustomerFinancialImgs().split(",");
                    for(int i =0;i<imgs.length;i++){
                        ImageFile imageFile = new ImageFile();
                        imageFile.setUrl(RequestUrl.file_image+imgs[i]);
                        imgs_reportCustomerFinancial.add(imageFile);
                    }
                    adapter_reportCustomerFinancial.notifyDataSetChanged();
                }

                if(reportImage!=null&&!TextUtils.isEmpty(reportImage.getBusinessManageImgs())){
                    String[] imgs = reportImage.getBusinessManageImgs().split(",");
                    for(int i =0;i<imgs.length;i++){
                        ImageFile imageFile = new ImageFile();
                        imageFile.setUrl(RequestUrl.file_image+imgs[i]);
                        imgs_reportBusinessManage.add(imageFile);
                    }
                    adapter_reportBusinessManage.notifyDataSetChanged();
                }

                if(reportImage!=null&&!TextUtils.isEmpty(reportImage.getCumtomerQualityImgs())){
                    String[] imgs = reportImage.getCumtomerQualityImgs().split(",");
                    for(int i =0;i<imgs.length;i++){
                        ImageFile imageFile = new ImageFile();
                        imageFile.setUrl(RequestUrl.file_image+imgs[i]);
                        imgs_reportCumtomerQuality.add(imageFile);
                    }
                    adapter_reportCumtomerQuality.notifyDataSetChanged();
                }

                if(reportImage!=null&&!TextUtils.isEmpty(reportImage.getGuaranteeImgs())){
                    String[] imgs = reportImage.getGuaranteeImgs().split(",");
                    for(int i =0;i<imgs.length;i++){
                        ImageFile imageFile = new ImageFile();
                        imageFile.setUrl(RequestUrl.file_image+imgs[i]);
                        imgs_reportGuarantee.add(imageFile);
                    }
                    adapter_reportGuarantee.notifyDataSetChanged();
                }

                if(reportImage!=null&&!TextUtils.isEmpty(reportImage.getFinanceImgs())){
                    String[] imgs = reportImage.getFinanceImgs().split(",");
                    for(int i =0;i<imgs.length;i++){
                        ImageFile imageFile = new ImageFile();
                        imageFile.setUrl(RequestUrl.file_image+imgs[i]);
                        imgs_reportFinance.add(imageFile);
                    }
                    adapter_reportFinance.notifyDataSetChanged();
                }
            }else {
                ToastUtils.showSystemToast(mContext, "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open(ImageFile position) {
        Intent intent = new Intent(mContext, ImageActivity.class);
        intent.putExtra("url",position.getUrl());
        mContext.startActivity(intent);
    }
}
