package com.dong.foodsect.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.dong.foodsect.R;

/**
 * Created by dllo on 16/11/30.
 */
public class HomeDetailsWebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView backIv;

    @Override
    int setLayout() {
        return R.layout.activity_home_page_webview;
    }

    @Override
    void initView() {
        webView = bindView(R.id.home_page_details_web);
        backIv = bindView(R.id.home_web_back);
        backIv.setOnClickListener(this);
    }

    @Override
    void initData() {
        getWebViewData();


    }

    private void getWebViewData() {
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }
        });

        // 设置webview加载网页的属性 websettings
        webView.loadUrl(link);
        WebSettings set = webView.getSettings();
        // 让WebView能够执行javaScript
        set.setJavaScriptEnabled(true);

        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        set.setAllowFileAccess(true);
        // 设置缓存
        set.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        set.setSupportZoom(true);
        // 将图片调整到合适的大小
        set.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 设置可以被显示的屏幕控制
        set.setDisplayZoomControls(true);
        // 设置默认字体大小
        set.setDefaultFontSize(12);
        // webView.getSettings().setSupportZoom(false);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
