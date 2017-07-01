package com.fyjf.all.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.fyjf.all.R;
import com.fyjf.all.utils.DialogUtils;
import com.fyjf.utils.NetworkUtils;


/**
 * Created by 任伟伟
 * Datetime: 2016/11/5-11:17
 * Email: renweiwei@ufashion.com
 */

public class SimpleWebView extends FrameLayout implements LoadErrorView.OnReloadListener{
    public static final String JAVASCRIPT_NAME = "igoda";
    private Context mContext;
    private WebView webView;
    private ProgressBar progressBar;
    private LoadErrorView load_error;

    public SimpleWebView(Context context) {
        super(context);
        init(context,null,0);
    }

    public SimpleWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SimpleWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }
    private void init(Context context,AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.widget_simple_webview,this);
        webView = (WebView) findViewById(R.id.web_View);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        load_error = (LoadErrorView) findViewById(R.id.load_error);
        load_error.setOnReloadListener(this);
        setupWebView();
    }
    @Override
    public void onReload() {
        webView.reload();
    }
    private void setupWebView() {
        webView.setWebViewClient(new SimpleWebViewClient());
        webView.setWebChromeClient(new SimpleWebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);//支持插件

        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        //便页面支持缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();//多窗口
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片

        webView.addJavascriptInterface(new JavascriptObject(mContext),JAVASCRIPT_NAME);//此次不使用this
        webView.requestFocusFromTouch();//输入焦点
    }

    public void loadUrl(String url){
        if(!TextUtils.isEmpty(url)){
            webView.loadUrl(url);
        }
    }

    public void addJavascriptInterface(JavascriptObject object) {
        webView.addJavascriptInterface(object,JAVASCRIPT_NAME);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static class JavascriptObject{
        private Context mContext;

        public JavascriptObject(Context mContext) {
            this.mContext = mContext;
        }
        @JavascriptInterface
        public void tel(final String phoneNumber) {
            DialogUtils.showPromote2(mContext, "确定拨打："+phoneNumber, null, new DialogUtils.OnConfirmListener() {
                @Override
                public void onConfirmed() {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phoneNumber));
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private class SimpleWebViewClient extends WebViewClient{

        //在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
        //操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //重写此方法可以让webview处理https请求
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);//handler.cancel();
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if(GONE==load_error.getVisibility())load_error.setVisibility(VISIBLE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            if(GONE==load_error.getVisibility()) {
                load_error.setVisibility(VISIBLE);
            }
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }

        //这个事件就是开始载入页面调用的，通常我们可以在这设定一个loading的页面，告诉用户程序在等待网络响应
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        //在页面加载结束时调用。同样道理，我们知道一个页面载入完成，于是我们可以关闭loading 条，切换程序动作。
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(NetworkUtils.isNetworkAvailable(mContext)){
                if(VISIBLE==load_error.getVisibility())load_error.setVisibility(GONE);
                if(GONE==webView.getVisibility())webView.setVisibility(VISIBLE);
            }else{
                if(GONE==load_error.getVisibility())load_error.setVisibility(VISIBLE);
                if(VISIBLE==webView.getVisibility())webView.setVisibility(GONE);
            }
        }

        // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        //onFormResubmission(WebView view, Message dontResend, Message resend) //(应用程序重新请求网页数据)
        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            super.onFormResubmission(view, dontResend, resend);
        }

        //(更新历史记录)
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }

    }

    private class SimpleWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        /**
         * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”
         */
        public boolean onJsAlert(WebView view, String url, String message,JsResult result) {
            DialogUtils.showPromote1(mContext, message, new DialogUtils.OnConfirmListener() {
                @Override
                public void onConfirmed() {
                }
            });
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;
            // return super.onJsAlert(view, url, message, result);
        }

        public boolean onJsBeforeUnload(WebView view, String url,String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        /**
         * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
         */
        public boolean onJsConfirm(WebView view, String url, String message,final JsResult result) {
            DialogUtils.showPromote2(mContext, message, new DialogUtils.OnCancelListener() {
                @Override
                public void onCanceled() {
                    result.cancel();
                }
            }, new DialogUtils.OnConfirmListener() {
                @Override
                public void onConfirmed() {
                    result.confirm();
                }
            });
            return true;
            // return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////”
         * window.prompt('请输入您的域名地址', '618119.com');
         */
        public boolean onJsPrompt(WebView view, String url, final String message,String defaultValue, final JsPromptResult result) {
            DialogUtils.showPromote2(mContext, message, new DialogUtils.OnCancelListener() {
                @Override
                public void onCanceled() {
                    result.cancel();
                }
            }, new DialogUtils.OnConfirmListener() {
                @Override
                public void onConfirmed() {
                    result.confirm(message);
                }
            });
            return true;
            // return super.onJsPrompt(view, url, message, defaultValue,result);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }
}
