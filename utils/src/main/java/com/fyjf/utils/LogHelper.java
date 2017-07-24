package com.fyjf.utils;

import android.util.Log;

/**
 * 日志打印工具类
 * @author czf
 *
 */
public class LogHelper {
    private static final String TAG = "TAG";
//    public static boolean DEBUG = GoodaApplication.isDebug();
    public static boolean DEBUG = false;

    /**
     * 调试
     * @param msg
     */
    public static void logD(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
    
    /**
     * 调试
     * @param msg
     */
    public static void logD(Object object, String msg) {
        if (DEBUG) {
            Log.d(object.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 错误
     * @param msg
     */
    public static void logE(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }
    
    /**
     * 错误
     * @param msg
     */
    public static void logE(Object object, String msg) {
        if (DEBUG) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }

}