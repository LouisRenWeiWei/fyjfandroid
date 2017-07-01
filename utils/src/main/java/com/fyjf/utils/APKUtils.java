package com.fyjf.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;

/**
 * Created by ASUS on 2017/5/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class APKUtils {
    public static void install(Context context,String apk){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(apk)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void uninstall(Context context,String packageName){
        Uri packageURI = Uri.parse("package:"+packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }


}
