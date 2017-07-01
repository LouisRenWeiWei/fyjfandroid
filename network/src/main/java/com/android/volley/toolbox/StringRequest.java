/*
 * Copyright (C) 2011 The Android Open Source Project
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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;

import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 */
public class StringRequest extends Request<String> {
    /** Default charset for JSON request. */
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private Listener<String> mListener;
//    private final SoftReference<Listener<String>> mListener;
    private final String mRequestBody;

    public StringRequest(String url, Listener<String> responseListener, ErrorListener errorListener) {
        this(Method.GET, url,null, responseListener, errorListener);
    }

    public StringRequest(int method, String url,String requestBody, Listener<String> responseListener,ErrorListener errorListener) {
        this(method, url,requestBody,responseListener, errorListener,null);
    }

    public StringRequest(int method, String url,String requestBody, Listener<String> responseListener,
                         ErrorListener errorListener, Response.LoadingListener loadingListener) {
        super(method, url, errorListener, loadingListener);
        this.mRequestBody = requestBody;
        this.mListener = responseListener;
    }

    @Override
    protected void deliverResponse(String response) {
        if(mListener!=null)mListener.onResponse(response);
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
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }
}
