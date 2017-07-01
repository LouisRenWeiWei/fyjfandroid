package com.fyjf.all.activity.report;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.Report;
import com.fyjf.utils.JSONUtil;
import com.fyjf.utils.SDUtils;
import com.fyjf.vo.RequestUrl;
import com.fyjf.vo.loan.GetReportVO;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rey.material.widget.Button;
import com.rey.material.widget.ImageView;

import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class CreditReportActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pdfView_bank)
    PDFView pdfView_bank;
    @BindView(R.id.pdfView_social)
    PDFView pdfView_social;

    @BindView(R.id.btn_bank)
    RadioButton btn_bank;
    @BindView(R.id.btn_social)
    RadioButton btn_social;

    private String reportId;
    FileDownloadListener fileDownloadListener;

    Report report;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_credit_report;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfView_bank.recycle();
                pdfView_social.recycle();
                finish();
            }
        });
        btn_bank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pdfView_social.setVisibility(View.GONE);
                    pdfView_bank.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_social.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pdfView_bank.setVisibility(View.GONE);
                    pdfView_social.setVisibility(View.VISIBLE);
                }
            }
        });
        fileDownloadListener = new FileDownloadListener() {
            private int preBytes = 0;
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                preBytes = 0;
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                String tag = (String) task.getTag();
                if(report!=null&&!TextUtils.isEmpty(tag)){
                    if(tag.equalsIgnoreCase(report.getBankCreditReport())){
                        pdfView_bank.fromFile(new File(task.getPath())) .defaultPage(0)
                                .onPageChange(CreditReportActivity.this)
                                .enableAnnotationRendering(true)
                                .onLoad(CreditReportActivity.this)
                                .scrollHandle(new DefaultScrollHandle(CreditReportActivity.this))
                                .load();

                    }
                    if(tag.equalsIgnoreCase(report.getSocialCreditReport())){
                        pdfView_social.fromFile(new File(task.getPath())) .defaultPage(0)
                                .onPageChange(CreditReportActivity.this)
                                .enableAnnotationRendering(true)
                                .onLoad(CreditReportActivity.this)
                                .scrollHandle(new DefaultScrollHandle(CreditReportActivity.this))
                                .load();
                    }
                }

            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
            }

            @Override
            protected void warn(BaseDownloadTask task) {
            }
        };

        reportId = getIntent().getStringExtra("reportId");
        if(!TextUtils.isEmpty(reportId)){
            getData();
        }
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    private void getData() {
        GetReportVO vo = new GetReportVO();
        vo.addParameter("id", reportId);
        vo.request(CreditReportActivity.this, "resp", "error");
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
                report = JSONUtil.toBean(resp.getJSONObject("data").getJSONObject("report"),Report.class);
                if(report!=null){
                    if(!TextUtils.isEmpty(report.getBankCreditReport())){
                        if(!TextUtils.isEmpty(reportId)){
                            String sdPath = SDUtils.getPDFPath()+report.getBankCreditReport();
                            FileDownloader.getImpl().create(RequestUrl.pdf_file_upload+report.getBankCreditReport()).setPath(sdPath)
                                    .setTag(report.getBankCreditReport())
                                    .setListener(fileDownloadListener)
                                    .ready();
                            FileDownloader.getImpl().start(fileDownloadListener, true);
                        }
                    }
                    if(!TextUtils.isEmpty(report.getSocialCreditReport())){
                        if(!TextUtils.isEmpty(reportId)){
                            String sdPath = SDUtils.getPDFPath()+report.getSocialCreditReport();
                            FileDownloader.getImpl().create(RequestUrl.pdf_file_upload+report.getSocialCreditReport()).setPath(sdPath)
                                    .setTag(report.getSocialCreditReport())
                                    .setListener(fileDownloadListener)
                                    .ready();
                            FileDownloader.getImpl().start(fileDownloadListener, true);
                        }
                    }
                }
            } else {
                ToastUtils.showSystemToast(mContext, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
