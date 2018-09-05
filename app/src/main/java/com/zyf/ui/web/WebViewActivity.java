package com.zyf.ui.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.biz.base.BaseActivity;
import com.zyf.app.App;
import com.zyf.ui.BuildConfig;
import com.zyf.driver.ui.R;
import com.zyf.util.SchemeUtil;

/**
 * Title: WebviewActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:18/05/2017  13:14
 *
 * @author johnzheng
 * @version 1.0
 */
public class WebViewActivity extends BaseActivity {

    public boolean startUri(Activity context, String urlHis) {
        return SchemeUtil.startUri(context,urlHis);
    }


    protected ProgressBar mPageLoadingProgressBar;
    protected WebView mWebView;
    protected String url = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_toolbar_layout);
        FrameLayout frameLayout = findViewById(R.id.frame_holder);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addView(mPageLoadingProgressBar =
                (ProgressBar) getLayoutInflater().inflate(R.layout.progressbar, appBarLayout, false), 1);
        frameLayout.addView(mWebView = new WebView(App.getAppContext()));

        Uri uri = getIntent().getData();
        if (uri != null) {
            url = uri.toString();
        }

        mToolbar.setNavigationIcon(R.drawable.vector_close);
        mToolbar.setNavigationOnClickListener(v -> {
            onBack();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
        WebSettings webSettings = mWebView.getSettings();
        //开启javascript
        webSettings.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        mWebView.setWebChromeClient(new MyChromeClient());
        mWebView.loadUrl(url);
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        setResult(RESULT_OK);
        finish();
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
            onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.setEnabled(false);
            view.postDelayed(() -> {
                view.setEnabled(true);
            }, 1000);
            if (startUri(getActivity(), url)) {
                view.stopLoading();
                return true;
            } else {
                view.loadUrl(url);
            }
            return false;
        }
    }

    class MyChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            mPageLoadingProgressBar.setProgress(i);
            if (mPageLoadingProgressBar != null && i != 100) {
                mPageLoadingProgressBar.setVisibility(View.VISIBLE);
            } else if (mPageLoadingProgressBar != null) {
                mPageLoadingProgressBar.setVisibility(View.GONE);
            }
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (!TextUtils.isEmpty(title)) {
                mToolbar.setTitle(title);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected void onBack(){
        finish();
    }
}
