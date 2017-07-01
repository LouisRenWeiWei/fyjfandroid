/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.ext.UploadFileInfo;
import com.android.volley.utils.CertificateConfig;
import com.android.volley.utils.KeyStoreUtils;
import com.android.volley.utils.SSLCertificateValidation;
import com.android.volley.utils.VolleySSLSocketFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * modify by ren weiwei
 * 2016-10-21
 */
public class Volley {

    /** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "igoda";

    private static volatile RequestQueue requestQueue;

    private static Context mContext;


    private static volatile Map<String, String> headers;

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link Context} to use for creating the cache dir.
     * @return A started {@link RequestQueue} instance.
     */
    private static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link Context} to use for creating the cache dir.
     * @param stack An {@link HttpStack} to use for the network, or null for default.
     * @return A started {@link RequestQueue} instance.
     */
    private static RequestQueue newRequestQueue(Context context, HttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        String userAgent = "igoda/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
            try {
                headers = new HashMap<>();
                TelephonyManager TelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                String szImei = TelephonyMgr.getDeviceId();
                WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                int screenWidth = display.getWidth();
                int screenHeight = display.getHeight();
                headers.put("mv","android");
                headers.put("v","1.11");
                headers.put("tid",szImei);
                headers.put("Charset", "UTF-8");
                headers.put("os","android");
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                headers.put("screenSize", screenWidth+","+screenHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 9) {
            VolleyLog.d("Volley use HttpURLConnection.......");
            if (CertificateConfig.ssl) {
                stack = new HurlStack(null, VolleySSLSocketFactory.getSSLSocketFactory(context));
            } else {
                stack = new HurlStack();
                SSLCertificateValidation.trustAllCertificate();
            }
        } else {
            // Prior to Gingerbread, HttpUrlConnection was unreliable.
            // See:
            // http://android-developers.blogspot.com/2011/09/androids-http-clients.html
            VolleyLog.d("Volley use httpClient.......");
            if (CertificateConfig.ssl)
                stack = new HttpClientStack(getHttpClient(context));
            else {
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();

        return queue;
    }

    private static HttpClient getHttpClient(Context context) {
        try {
            SSLSocketFactory sslSocketFactory = new SSLSocketFactory(
                    KeyStoreUtils.getKeyStore(context, CertificateConfig.keyStoreFileName,CertificateConfig.keyStorePassword,CertificateConfig.KEY_STORE_TYPE_P12,null),
                    CertificateConfig.keyStorePassword,
                    KeyStoreUtils.getKeyStore(context,CertificateConfig.trustStoreFileName,CertificateConfig.trustStorePassword,CertificateConfig.KEY_STORE_TYPE_BKS,null));
            HttpParams params = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https",sslSocketFactory, 443));
            ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
            return new DefaultHttpClient(cm, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在application的 oncreate中初始化
     * @param context
     */
    public static void init(Context context,boolean debug){
        mContext = context;
        VolleyLog.DEBUG = debug;
    }

    private static RequestQueue getRequestQueue(){
        if (requestQueue==null){
            synchronized (Volley.class){
                if(requestQueue==null){
                    requestQueue = newRequestQueue(mContext);
                }
            }
        }
        return requestQueue;
    }

    private static void add(Request request) {
        getRequestQueue().add(request);
    }

    private static Map<String, String> getAppHeaders(){
        return headers;
    }

    /**
     *
     * @param method  请求方法
     * @param url 请求地址
     * @param requestBody  请求body 可以为空
     * @param listener  回调
     */
    public static void addRequest(int method, String url,String requestBody,ResponseListener listener){
        StringRequest request = new StringRequest(method,url,requestBody,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getAppHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,0,0));
        add(request);
    }

    public static void addRequest(int method, String url, JSONObject requestBody, ResponseListener listener){
        ObjectRequest request = new ObjectRequest(method,url,requestBody,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getAppHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,0,0));
        add(request);
    }
    /**
     *  get 请求
     * @param url 请求地址
     * @param requestBody  请求body 可以为空
     * @param listener  回调
     */
    public static void addGetRequest(String url,String requestBody,ResponseListener listener){
        StringRequest request = new StringRequest(Request.Method.GET,url,requestBody,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getAppHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,0,0));
        add(request);
    }
    /**
     * post 请求
     * @param url 请求地址
     * @param requestBody  请求body 可以为空
     * @param listener  回调
     */
    public static void addPostRequest(String url,String requestBody,ResponseListener listener){
        StringRequest request = new StringRequest(Request.Method.POST,url,requestBody,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getAppHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,0,0));
        add(request);
    }

    public static void uploadFile(UploadFileInfo requestInfo, ResponseListener listener, Response.LoadingListener loadingListener){
        MultiPartRequest request = null;
        if(Request.Method.POST==requestInfo.getMethod()){
            request = new MultiPartRequest(Request.Method.POST, requestInfo.getUrl(), listener, listener,loadingListener );
        }else if(Request.Method.GET==requestInfo.getMethod()) {
            request = new MultiPartRequest(Request.Method.POST, requestInfo.getFullUrl(), listener, listener,loadingListener );
            Map<String, String> paramsMap = requestInfo.getParams();
            if (paramsMap != null && paramsMap.size() != 0) {
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    request.addPart(entry.getKey(), entry.getValue());
                }
            }
        }

        Map<String, File> fileParams = requestInfo.getFileParams();
        if (fileParams != null && fileParams.size() != 0) {
            for (Map.Entry<String, File> entry : fileParams.entrySet()) {
                String key = entry.getKey();
                int index = key.indexOf(requestInfo.boundary);
                if (index > 0) {
                    key = key.substring(0, index);
                }
                request.addPart(key, entry.getValue());
            }
        }
        add(request);
    }


    public static void download(UploadFileInfo requestInfo, String targetFile, boolean isResume, ResponseListener listener, Response.LoadingListener loadingListener){
        DownloadRequest request = new DownloadRequest(requestInfo.getFullUrl(),listener,listener , loadingListener);
        request.setResume(isResume);
        request.setTarget(targetFile);
        add(request);
    }

}