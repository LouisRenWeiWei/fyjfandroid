package com.gooda.push;

import android.content.Context;

import java.util.UUID;

/**
 * Created by 任伟伟
 * Datetime: 2017/2/21-22:26
 * Email: renweiwei@ufashion.com
 */

public class DeviceUtil {
    public static String genDeviceId(Context context){
        return "Android_"+UUID.randomUUID().toString();
    }
}
