package com.fyjf.all.activity.overdue;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.adapter.OverdueProgressAdapter;
import com.fyjf.all.utils.ToastUtils;
import com.fyjf.dao.entity.OverdueProgress;
import com.fyjf.dao.entity.OverdueReport;
import com.fyjf.utils.JSONUtil;
import com.fyjf.utils.SDUtils;
import com.fyjf.vo.RequestUrl;
import com.fyjf.vo.overdue.OverduesVO;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rey.material.widget.ImageView;

import org.json.JSONObject;

import java.io.File;
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
                pdfView.fromFile(new File(task.getPath())) .defaultPage(0)
                        .onPageChange(OverdueReportActivity.this)
                        .enableAnnotationRendering(true)
                        .onLoad(OverdueReportActivity.this)
                        .scrollHandle(new DefaultScrollHandle(OverdueReportActivity.this))
                        .load();

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
            FileDownloader.getImpl().create(RequestUrl.pdf_file_upload + pdfPath).setPath(sdPath)
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
}
