package com.android.volley.ext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
/**
 * Created by 任伟伟
 * Datetime: 2016/10/29-13:22
 * Email: renweiwei@ufashion.com
 */
public class UploadFileInfo {

    public final String boundary = String.valueOf(System.currentTimeMillis());

    public String url ;
    public int method = Request.Method.GET;
    public Map<String,String> params = new HashMap<String, String>() ;
    public Map<String, File> fileParams = new HashMap<String, File>();

    public UploadFileInfo(String url, int method) {
        this.url = url;
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

    public String getBoundary() {
        return boundary;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, File> getFileParams() {
        return fileParams;
    }

    public String getFullUrl() {
        if (url != null && params != null) {
            StringBuilder sb = new StringBuilder();
            if (!url.contains("?")) {
                url = url + "?";
            } else {
                if (!url.endsWith("?")) {
                    url = url + "&";
                }
            }
            Iterator<String> iterotor = params.keySet().iterator();
            try {
                while (iterotor.hasNext()) {
                    String key = (String) iterotor.next();
                    if (key != null) {
                        if (params.get(key) != null) {
                            sb.append(URLEncoder.encode(key, "utf-8")).append("=")
                                    .append(URLEncoder.encode(params.get(key), "utf-8")).append("&");
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (sb.length() > 0 && sb.lastIndexOf("&") == sb.length() - 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return url + sb.toString();
        }
        return url;
    }

    public String getUrl() {
        return url;
    }


    public void put(String key, String value) {
        params.put(key, value);
    }

    public void putFile(String key, File file) {
        if (fileParams.containsKey(key)) {
            fileParams.put(key + boundary + fileParams.size()+System.currentTimeMillis(), file);
        } else {
            fileParams.put(key, file);
        }
    }
}
