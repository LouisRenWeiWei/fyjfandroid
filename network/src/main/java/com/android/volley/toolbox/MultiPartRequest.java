package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.Response.LoadingListener;
import com.android.volley.VolleyLog;
import com.android.volley.multipart.FilePart;
import com.android.volley.multipart.StringPart;
import com.android.volley.multipart.UploadMultipartEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/29-13:22
 * Email: renweiwei@ufashion.com
 */

public class MultiPartRequest extends Request<String> {

    private static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Listener object for delivering the response
     */
    private Listener<String> mListener;

    private UploadMultipartEntity mMultipartEntity;
    /**
     * Default connection timeout for Multipart requests
     */
    public static final int TIMEOUT_MS = 60000;

    public MultiPartRequest(int method, String url, Listener<String> listener, ErrorListener errorlistener, LoadingListener loadingListener) {

        super(method, url, errorlistener);
        mListener = listener;

        mMultipartEntity = new UploadMultipartEntity();

//        final ExecutorDelivery delivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
//        setLoadingListener(loadingListener);
//        if (loadingListener != null) {
//            mMultipartEntity.setListener(new UploadMultipartEntity.ProgressListener() {
//                long time = SystemClock.uptimeMillis();
//                long count = -1;
//
//                @Override
//                public void transferred(long num) {
//                    if (count == -1) {
//                        count = mMultipartEntity.getContentLength();
//                    }
//                    // LogUtils.d("bacy", "upload->" + count + ",num->" + num);
//                    long thisTime = SystemClock.uptimeMillis();
//                    if (thisTime - time >= getRate() || count == num) {
//                        time = thisTime;
//                        delivery.postLoading(MultiPartRequest.this, count, num);
//                    }
//                }
//            });
//        }
        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public String getBodyContentType() {
        return mMultipartEntity.getContentType().getValue();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }


    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    /**
     * Get the protocol charset
     */
    public String getProtocolCharset() {
        return PROTOCOL_CHARSET;
    }

    public void addPart(String key, String value) {
        StringPart part = new StringPart(key, value, PROTOCOL_CHARSET);
        mMultipartEntity.addPart(part);
    }

    public void addPart(String key, File file) {
        FilePart part = new FilePart(key, file, null, null);
        mMultipartEntity.addPart(part);
    }

    public UploadMultipartEntity getMultipartEntity() {
        return mMultipartEntity;
    }

    @Override
    public String toString() {
        return this.getUrl();//.substring(0, this.getUrl().indexOf("?"));
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 将mMultiPartEntity中的参数写入到bos中
            mMultipartEntity.writeTo(bos);
            return bos.toByteArray();
        } catch (Exception e) {
            VolleyLog.e("", "IOException writing to ByteArrayOutputStream");
            return null;
        }
    }
}

