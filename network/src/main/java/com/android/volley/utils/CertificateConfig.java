package com.android.volley.utils;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/26-14:06
 * Email: renweiwei@ufashion.com
 */
public interface CertificateConfig {
    public static final boolean ssl = false;
    public static final String KEY_STORE_TYPE_BKS = "bks";
    public static final String KEY_STORE_TYPE_P12 = "PKCS12";

    //key
    public static final String keyStoreFileName = "goda.p12";
    public static final String keyStorePassword = "igoda2016";//"123456";
    public static final String alias = null;//"client";

    public static final String trustStoreFileName = "igoda.bks";
    public static final String trustStorePassword = "igoda2016";//"123456";

}
