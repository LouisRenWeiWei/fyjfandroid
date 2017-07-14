package com.fyjf.all.app;

import android.app.Application;
import android.content.Context;

import com.android.volley.toolbox.Volley;
import com.antfortune.freeline.FreelineCore;
import com.liulishuo.filedownloader.FileDownloader;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application{

	private Context mContext;

	public void onCreate() {
		super.onCreate();
		mContext = this;
		FreelineCore.init(this);
		AppData.init(this);
		Volley.init(this,false);
		FileDownloader.init(this);
		JPushInterface.init(this);//极光推送
		JPushInterface.setDebugMode(true);//极光送Log
	}
}