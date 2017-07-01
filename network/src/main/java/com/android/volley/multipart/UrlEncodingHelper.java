package com.android.volley.multipart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.protocol.HTTP;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/29-13:22
 * Email: renweiwei@ufashion.com
 */
public class UrlEncodingHelper {

    public static String encode(final String content, final String encoding) {
        try {
            return URLEncoder.encode(
                content, 
                encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET
            );
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }
    
}
