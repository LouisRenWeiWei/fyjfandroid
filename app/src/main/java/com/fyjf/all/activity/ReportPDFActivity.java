package com.fyjf.all.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.activity.report.ReportMsgActivity;
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
public class ReportPDFActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.customer_msg)
    TextView customer_msg;

    private String reportId;
    private String reportPDF;

    FileDownloadListener fileDownloadListener;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_report;
    }

    @Override
    protected void preInitData() {
        reportId = getIntent().getStringExtra("reportId");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfView.recycle();
                finish();
            }
        });
        customer_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",reportId);
                startActivity(ReportMsgActivity.class,bundle);
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
                try {
                    pdfView.fromFile(new File(task.getPath())) .defaultPage(0)
                            .onPageChange(ReportPDFActivity.this)
                            .onLoad(ReportPDFActivity.this)
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

        reportPDF = RequestUrl.file_pdf_report+reportId+RequestUrl.pdf_file_ext;
        if(!TextUtils.isEmpty(reportId)){
            String sdPath = SDUtils.getPDFPath()+reportId+RequestUrl.pdf_file_ext;
            FileDownloader.getImpl().create(reportPDF).setPath(sdPath)
                    .setTag(reportId)
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
