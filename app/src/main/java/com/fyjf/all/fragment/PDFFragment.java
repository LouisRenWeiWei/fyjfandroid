package com.fyjf.all.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.fyjf.all.R;
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

/**
 * Created by ASUS on 2017/7/23.
 */

public class PDFFragment extends BaseFragment implements OnPageChangeListener,OnLoadCompleteListener {
    private String pdf;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    FileDownloadListener fileDownloadListener;

    public static  PDFFragment getInstance(String pdf){
        PDFFragment fragment = new PDFFragment();
        Bundle args = new Bundle();
        args.putString("pdf",pdf);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_pdf;
    }



    @Override
    protected void preInitData() {
        pdfView.setEnabled(false);
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
                            .onPageChange(PDFFragment.this)
                            .onLoad(PDFFragment.this)
                            .onError(new OnErrorListener() {
                                @Override
                                public void onError(Throwable t) {
                                    Log.e("tag---pdf error----",t.getMessage());
                                }
                            })
                            .load();
                    pdfView.setEnabled(true);
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
        Bundle bundle = getArguments();
        if(bundle!=null){
            pdf = bundle.getString("pdf","");
            if(!TextUtils.isEmpty(pdf)){
                String sdPath = SDUtils.getPDFPath()+pdf;
                FileDownloader.getImpl().create(RequestUrl.file_pdf+pdf).setPath(sdPath)
                        .setTag(pdf)
                        .setListener(fileDownloadListener)
                        .ready();
                FileDownloader.getImpl().start(fileDownloadListener, true);
            }
        }
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
