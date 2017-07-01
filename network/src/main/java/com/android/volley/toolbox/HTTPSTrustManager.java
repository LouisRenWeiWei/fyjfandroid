package com.android.volley.toolbox;

import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/24-11:14
 * Email: renweiwei@ufashion.com
 */

public class HTTPSTrustManager implements X509TrustManager {
    private static TrustManager[] trustManagers;
    private static final X509Certificate[] _AcceptedIssusers = new X509Certificate[]{};
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    public boolean isClientTrusted(X509Certificate[] chain) {
        return true;
    }

    public boolean isServerTrusted(X509Certificate[] chain) {
        return true;
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return _AcceptedIssusers;
    }

    public static void allowAllSSL(){
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        SSLContext context = null;
        if(trustManagers==null){
            trustManagers = new TrustManager[]{new HTTPSTrustManager()};
        }

        try {
            context = SSLContext.getDefault();
            context.init(null,trustManagers,new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

    }
}
