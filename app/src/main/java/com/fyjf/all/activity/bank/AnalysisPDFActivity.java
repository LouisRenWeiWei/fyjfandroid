package com.fyjf.all.activity.bank;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.dao.entity.BankAnalysis;
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

public class AnalysisPDFActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    @BindView(R.id.iv_back)
    ImageView iv_back;

    private BankAnalysis bankAnalysis;

    FileDownloadListener fileDownloadListener;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_analysis_pdf;
    }

    @Override
    protected void preInitData() {

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                try {
                    pdfView.fromFile(new File(task.getPath())) .defaultPage(0)
                            .onPageChange(AnalysisPDFActivity.this)
                            .onLoad(AnalysisPDFActivity.this)
                            .onError(new OnErrorListener() {
                                @Override
                                public void onError(Throwable t) {
                                    Log.e("tag---pdf error----",t.getMessage());
                                    dismisDialog();
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
                dismisDialog();
            }

            @Override
            protected void warn(BaseDownloadTask task) {
            }
        };

        bankAnalysis = (BankAnalysis) getIntent().getSerializableExtra("bankAnalysis");
        if(bankAnalysis!=null){
            tv_title.setText(bankAnalysis.getBankName()+bankAnalysis.getTitle());
            if(!TextUtils.isEmpty(bankAnalysis.getPdfFile())){
                showDialog("正在加载，请稍后...");
                String sdPath = SDUtils.getPDFPath()+bankAnalysis.getPdfFile();
                FileDownloader.getImpl().create(RequestUrl.file_pdf+bankAnalysis.getPdfFile()).setPath(sdPath)
                        .setTag(bankAnalysis.getPdfFile())
                        .setListener(fileDownloadListener)
                        .ready();
                FileDownloader.getImpl().start(fileDownloadListener, true);
            }
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {
        dismisDialog();
    }
}
