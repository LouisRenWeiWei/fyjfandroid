package com.fyjf.all.activity.overdue;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
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
public class OverduePDFActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    @BindView(R.id.tv_send_msg)
    TextView  tv_send_msg;

    FileDownloadListener fileDownloadListener;

    private String pdfPath;
    private String overdueId;


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

        tv_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(overdueId)){
                    Intent intent = new Intent(mContext,OverdueMsgActivity.class);
                    intent.putExtra("overdueId",overdueId);
                    startActivity(intent);
                }

            }
        });

        pdfPath = getIntent().getStringExtra("pdfPath");
        overdueId = getIntent().getStringExtra("overdueId");

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
                            .onPageChange(OverduePDFActivity.this)
                            .onLoad(OverduePDFActivity.this)
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
                dismisDialog();
            }

            @Override
            protected void warn(BaseDownloadTask task) {
            }
        };

        if (!TextUtils.isEmpty(pdfPath)) {
            showDialog("正在加载，请稍后...");
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
        dismisDialog();
    }

//    @Override
//    public void openPDF(int position) {
//
//    }
}
