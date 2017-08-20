package com.fyjf.all.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.android.volley.toolbox.Volley;
import com.antfortune.freeline.FreelineCore;
import com.blankj.utilcode.util.Utils;
import com.liulishuo.filedownloader.FileDownloader;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application{

	private Context mContext;

	public void onCreate() {
		super.onCreate();
		mContext = this;
		try {
			FreelineCore.init(this);
			AppData.init(this);
			Volley.init(this,false);
			Utils.init(this);//工具类
			FileDownloader.init(this);
			JPushInterface.init(this);//极光推送
			JPushInterface.setDebugMode(true);//极光送Log
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}