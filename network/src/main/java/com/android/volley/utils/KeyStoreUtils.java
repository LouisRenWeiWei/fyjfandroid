package com.android.volley.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/26-14:06
 * Email: renweiwei@ufashion.com
 */
public class KeyStoreUtils {

    public static KeyStore getKeyStore(Context context,String keyStoreFileName, String keyStorePassword,String keyStoreType, CertificateEncryption encryption){
        KeyStore keyStore = null;
        try {
            InputStream inputStream = context.getResources().getAssets().open(keyStoreFileName);
            if (encryption != null) {
                inputStream = encryption.decode( inputStream );
            }
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(inputStream, keyStorePassword.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyStore;
    }
}
