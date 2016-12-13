package com.dong.foodsect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dong.foodsect.R;
import com.dong.foodsect.volleydemo.CollectBean;
import com.dong.foodsect.volleydemo.DBTool;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class DelicacyDetailsActivity extends BaseActivity implements View.OnClickListener {



    private String link;
    private String title;

    private WebView webView;
    private ImageView backIv,heartIv;
    private LinearLayout linearLayout, sharell;
    private int i;

    @Override
    int setLayout() {
        return R.layout.activity_evaluation_details;
    }

    @Override
    void initView() {

        webView = (WebView) findViewById(R.id.evaluation_web);
        backIv = (ImageView) findViewById(R.id.eva_back);
        linearLayout = (LinearLayout) findViewById(R.id.collect_ll);
        sharell = (LinearLayout) findViewById(R.id.eva_share_ll);
        heartIv = bindView(R.id.iv_news_keep_defaultiv);
        sharell.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    void initData() {
        getWebViewData();


        if (!DBTool.getInstance().isUrlSave(link)) {
            i = 1;
            heartIv.setSelected(false);
        } else {
            i = 2;
            heartIv.setSelected(true);
        }
    }


    // 第三方分享

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    private void getWebViewData() {
        Intent intent = getIntent();
        link = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        Log.d("sss", link);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.eva_back:
                finish();
                break;
            case R.id.collect_ll:
                if (i % 2 != 0) {
                    // 没存 要存 单数
                    heartIv.setSelected(true);
                    CollectBean collectBean = new CollectBean(null, link,title);
                    DBTool.getInstance().insertUrl(collectBean);
                    i = i + 1;
                } else {
                    // 存过 要删 双数
                    heartIv.setSelected(false);
                    i = i + 1;
                    DBTool.getInstance().deleteUrlBy(link);
                }
                break;
            case R.id.eva_share_ll:
                showShare();
                break;
        }

    }
}
