package com.fyjf.vo;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.ext.ResponseError;
import com.android.volley.ext.ResponseSuccess;
import com.android.volley.toolbox.ResponseListener;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/28-14:09
 * Email: rww_2010@126.com
 */
public abstract class BaseVO {
    public static Map<String,Method> callBackCache = new HashMap<>();//object-method-name
    protected String url;
    protected int method = Request.Method.GET;
    protected JSONObject param;
    public BaseVO() {
        setup();
        this.method = Request.Method.POST;
    }
    protected abstract void setup();
    public void setUrl(String url){
        this.url = url;
    }

    public void addParameter(String key, Object value){
        try {
            if(param==null)param = new JSONObject();
            param.put(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void request(Object thiz, String successCallback, String errorCallback){
        String successKey = thiz.getClass().getSimpleName()+"-"+successCallback;
        String errorKey = thiz.getClass().getSimpleName()+"-"+errorCallback;
        Method successMethod = callBackCache.get(successKey);
        Method errorMethod = callBackCache.get(errorKey);
        if(successMethod==null||errorMethod==null){
            Method[] methods = thiz.getClass().getDeclaredMethods();
            if(successMethod==null){
                for(int i=0;i<methods.length;i++){
                    Method method = methods[i];
                    if(method.isAnnotationPresent(ResponseSuccess.class)&&method.getName().equals(successCallback)){
                        successMethod = method;
                        callBackCache.put(successKey,successMethod);
                        break;
                    }
                }
            }
            if(errorMethod==null){
                for(int i=0;i<methods.length;i++){
                    Method method = methods[i];
                    if(method.isAnnotationPresent(ResponseError.class)&&method.getName().equals(errorCallback)){
                        errorMethod = method;
                        callBackCache.put(errorKey,errorMethod);
                        break;
                    }
                }
            }
        }
        Volley.addRequest(method,url,param,new Response(thiz,successMethod,errorMethod));
    }

    private static class Response implements ResponseListener {
        private Method successMethod,errorMethod;
        private Object object;

        public Response(Object object, Method successMethod, Method errorMethod) {
            this.object = object;
            this.successMethod = successMethod;
            this.errorMethod = errorMethod;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            try {
                 if(errorMethod!=null){
                     errorMethod.setAccessible(true);
                     errorMethod.invoke(object,error);
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onResponse(String response) {
            try {
                 if(successMethod!=null){
                     successMethod.setAccessible(true);
                     successMethod.invoke(object,response);
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
