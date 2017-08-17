package com.chh.potoyang.first.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chh.potoyang.config.ActivityURL;
import com.chh.potoyang.first.R;
import com.chh.potoyang.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by potoyang on 2017/8/11.
 */

@Route(path = ActivityURL.WebExtendActivity)
public class WebExtendActivity extends BaseActivity {

    private final static String TAG = WebExtendActivity.class.getSimpleName();

    @Bind(R.id.webViewBaidu)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webextend);
        ButterKnife.bind(this);
        showGoBackLayout();
        init();
    }

    private void init() {
        setToolbarTitleText("百度首页");
        initWebview();
        webView.loadUrl("http://www.baidu.com");
    }

    private void initWebview() {
        webView.getSettings().setJavaScriptEnabled(true);//执行JS脚本
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setGeolocationEnabled(false);

        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setDomStorageEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);//设置使支持缩放
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 使用当前WebView处理跳转
                return true;//true表示此事件在此处被处理，不需要再广播
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                LogUtils.i(TAG, "发生错误:" + error);
            }

        });
    }
}
