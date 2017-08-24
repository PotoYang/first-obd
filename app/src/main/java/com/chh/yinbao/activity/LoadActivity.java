package com.chh.yinbao.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chh.yinbao.R;
import com.chh.yinbao.config.ActivityURL;
import com.chh.yinbao.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by potoyang on 2017/8/24.
 */

@Route(path = ActivityURL.LoadActivity)
public class LoadActivity extends BaseActivity {

    private final String TAG = LoadActivity.class.getSimpleName();
    private String token;

    @Bind(R.id.webViewLoad)
    WebView webViewLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        ButterKnife.bind(this);
        showGoBackLayout();
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        token = bundle.getString("token");
        initWebview();
        webViewLoad.loadUrl("http://yinbao.senit.xyz:8080/yinbao/my_youhui.html");
    }

    private void initWebview() {
        webViewLoad.getSettings().setJavaScriptEnabled(true);//执行JS脚本
        webViewLoad.getSettings().setAllowFileAccess(false);
        webViewLoad.getSettings().setGeolocationEnabled(false);

        webViewLoad.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webViewLoad.getSettings().setUseWideViewPort(true);
        webViewLoad.getSettings().setLoadWithOverviewMode(true);

        webViewLoad.getSettings().setDomStorageEnabled(false);
        webViewLoad.setWebChromeClient(new WebChromeClient());
        webViewLoad.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                url.startsWith();
//                view.loadUrl(url);// 使用当前WebView处理跳转
//                return true;//true表示此事件在此处被处理，不需要再广播
                String mineTag = "http://yinbao.senit.xyz:8080/yinbao/my_mine.html";

//                if (url.equals(mineTag)) {
//                    String mobile = url.substring(url.lastIndexOf("/") + 1);
//                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
//                    startActivity(phoneIntent);
//                    //这个超连接,java已经处理了，webview不要处理了
//                    return true;
//                }
                if (url.equals(mineTag)) {
                    System.out.println(mineTag);
                    Map<String, String> map = new HashMap<>();
                    map.put("token", token);
                    view.loadUrl("http://yinbao.senit.xyz:8080/yinbao/my_mine.html", map);
//                    view.loadUrl("http://yinbao.senit.xyz:8080/yinbao/my_mine.html?token=" + token);
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                LogUtils.i(TAG, "发生错误:" + error);
            }

        });
    }
}
