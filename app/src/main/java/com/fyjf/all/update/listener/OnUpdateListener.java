package com.fyjf.all.update.listener;


import com.fyjf.dao.entity.AppVersion;

/**
 * Created by Shelwee on 14-5-16.
 */
public interface OnUpdateListener {
    /**
     * on start check
     */
    public void onStartCheck();

    /**
     * on finish check
     */
    public void onFinishCheck(AppVersion info);

    public void onStartDownload();
    
    public void onDownloading(int progress);
    
    public void onFinshDownload();

}
