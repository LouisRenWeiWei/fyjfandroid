package com.fyjf.all.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fyjf.utils.StatisticsUtils;
import com.wwren.dialog.SweetAlert.SweetAlertDialog;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
	protected Context mContext;
	protected View contentView;
	protected SweetAlertDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView = inflater.inflate(getContentLayout(),container,false);
		mContext = getActivity();
		ButterKnife.bind(this,contentView);
		preInitData();
		return contentView;
	}

	protected void showDialog(String msg){
		if(dialog==null){
			dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
			dialog.getProgressHelper().setBarColor(Color.parseColor("#3A558E"));
			dialog.setCancelable(false);
		}
		dialog.setTitleText(msg);
		dialog.show();
	}

	protected void dismisDialog(){
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismissWithAnimation();
		}
	}

	protected abstract int getContentLayout();

	protected abstract void preInitData();


	@Override
	public void onResume() {
		super.onResume();
		StatisticsUtils.fragmentOnResume(this.getClass().getSimpleName());
	}

	@Override
	public void onPause() {
		super.onPause();
		StatisticsUtils.fragmentOnPause(this.getClass().getSimpleName());
	}
	protected <T extends Activity> void  startActivity(Class<T> clazz){
		Intent intent = new Intent(mContext,clazz);
		startActivity(intent);
	}
	protected <T extends Activity> void  startActivity(Class<T> clazz,Bundle bundle){
		Intent intent = new Intent(mContext,clazz);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
