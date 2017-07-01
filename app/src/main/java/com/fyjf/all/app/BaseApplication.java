package com.fyjf.all.app;

import android.app.Application;

import com.android.volley.toolbox.Volley;
import com.antfortune.freeline.FreelineCore;
import com.liulishuo.filedownloader.FileDownloader;

public class BaseApplication extends Application{

	public void onCreate() {
		super.onCreate();
		FreelineCore.init(this);
		AppData.init(this);
		Volley.init(this,false);
		FileDownloader.init(this);
	}
}