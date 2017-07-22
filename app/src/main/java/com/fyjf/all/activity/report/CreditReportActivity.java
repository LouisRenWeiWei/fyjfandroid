package com.fyjf.all.activity.report;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.dao.entity.CustomerReportInfo;
import com.fyjf.utils.SDUtils;
import com.fyjf.vo.RequestUrl;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import butterknife.BindView;

//import com.joanzapata.pdfview.PDFView;
//import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
//import com.joanzapata.pdfview.listener.OnPageChangeListener;

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

    private CustomerReportInfo report;

    private String reportId;
    FileDownloadListener fileDownloadListener;

//    Report report;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_credit_report;
    }

    @Override
    protected void preInitData() {
        Intent intent = getIntent();
        if (intent!=null){
            report = (CustomerReportInfo) intent.getSerializableExtra("report");
        }
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
                Log.d("download_pdf","----soFarBytes: "+soFarBytes + "   totalBytes: "+totalBytes);
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                String tag = (String) task.getTag();
                if(report!=null&&!TextUtils.isEmpty(tag)){
                    if(tag.equalsIgnoreCase(report.getBankCreditReport())){
                        try {
                            pdfView_bank.fromFile(new File(task.getPath())).defaultPage(0)
                                    .onPageChange(CreditReportActivity.this)
                                    .onLoad(CreditReportActivity.this)
                                    .onError(new OnErrorListener() {
                                        @Override
                                        public void onError(Throwable t) {
                                            Log.e("tag---pdf error----",t.getMessage());
                                        }
                                    })
                                    .load();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else if(tag.equalsIgnoreCase(report.getSocialCreditReport())){
                        try {
                            pdfView_social.fromFile(new File(task.getPath())).defaultPage(0)
                                    .onPageChange(CreditReportActivity.this)
                                    .onLoad(CreditReportActivity.this)
                                    .onError(new OnErrorListener() {
                                        @Override
                                        public void onError(Throwable t) {
                                            Log.e("tag---pdf error----",t.getMessage());
                                        }
                                    })
                                    .load();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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


        pdfView_bank.setVisibility(View.GONE);
        pdfView_social.setVisibility(View.VISIBLE);

        if(!TextUtils.isEmpty(report.getBankCreditReport())){
            String sdPath = SDUtils.getPDFPath()+report.getBankCreditReport();
            FileDownloader.getImpl().create(RequestUrl.file_pdf+report.getBankCreditReport()).setPath(sdPath)
                    .setTag(report.getBankCreditReport())
                    .setListener(fileDownloadListener)
                    .ready();
            FileDownloader.getImpl().start(fileDownloadListener, true);
        }
        if(!TextUtils.isEmpty(report.getSocialCreditReport())){
            String sdPath = SDUtils.getPDFPath()+report.getSocialCreditReport();
            FileDownloader.getImpl().create(RequestUrl.file_pdf+report.getSocialCreditReport()).setPath(sdPath)
                    .setTag(report.getSocialCreditReport())
                    .setListener(fileDownloadListener)
                    .ready();
            FileDownloader.getImpl().start(fileDownloadListener, true);
        }
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }


}
