package com.fyjf.utils;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;


/**
 * Created by 任伟伟
 * Datetime: 2016/10/26-10:32
 * Email: renweiwei@ufashion.com
 */
public class StatisticsUtils {

    /**
     * 仅有activity的统计
     * @param context
     * @param pageName
     */
    public static void onResume(Context context,String pageName){
        MobclickAgent.onPageStart(pageName); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(context);          //统计时长
    }
    /**
     * 使用FragmentActivity + Fragment实现的，需要在 FragmentActivity 中统计时长
     * @param context
     */
    public static void onPause(Context context,String pageName){
        MobclickAgent.onPause(context);
    }

    public static void onResume(Context context){
        MobclickAgent.onResume(context);          //统计时长
    }
    /**
     * 使用FragmentActivity + Fragment实现的，需要在 FragmentActivity 中统计时长
     * @param context
     */
    public static void onPause(Context context){
        MobclickAgent.onPause(context);
    }

    /**
     * Fragment 中统计页面：
     * @param name
     */
    public static void fragmentOnResume(String name){
        MobclickAgent.onPageStart(name); //统计页面，"MainScreen"为页面名称，可自定义
    }
    /**
     * Fragment 中统计页面：
     * @param name
     */
    public static void fragmentOnPause(String name){
        MobclickAgent.onPageEnd(name);
    }
}