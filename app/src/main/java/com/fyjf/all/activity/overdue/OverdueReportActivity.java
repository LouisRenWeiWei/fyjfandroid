package com.fyjf.all.activity.overdue;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.OverdueProgressAdapter;
import com.fyjf.utils.SDUtils;
import com.fyjf.vo.RequestUrl;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

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
public class OverdueReportActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener,OverdueProgressAdapter.ItemOperationListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pdfView)
    PDFView pdfView;


    FileDownloadListener fileDownloadListener;

    private String pdfPath;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_overdue_report;
    }

    @Override
    protected void preInitData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        pdfPath = getIntent().getStringExtra("pdfPath");

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
               // String tag = (String) task.getTag();
                try {
                    pdfView.fromFile(new File(task.getPath())) .defaultPage(0)
                            .onPageChange(OverdueReportActivity.this)
                            .onLoad(OverdueReportActivity.this)
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

        if (!TextUtils.isEmpty(pdfPath)) {
            String sdPath = SDUtils.getPDFPath() + pdfPath;
            FileDownloader.getImpl().create(RequestUrl.file_pdf + pdfPath).setPath(sdPath)
                    .setTag(pdfPath)
                    .setListener(fileDownloadListener)
                    .ready();
            FileDownloader.getImpl().start(fileDownloadListener, true);
        }

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void openReport(int position) {

    }

    @Override
    public void openImg(int position) {

    }

    @Override
    public void openMsg(int position) {

    }
}
