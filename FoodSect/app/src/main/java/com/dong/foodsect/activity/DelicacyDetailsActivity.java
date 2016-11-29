package com.dong.foodsect.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.dong.foodsect.R;

public class DelicacyDetailsActivity extends BaseActivity {


    private WebView webView;
    @Override
    int setLayout() {
        return R.layout.activity_delicacy_details;
    }

    @Override
    void initView() {
        webView = (WebView) findViewById(R.id.delicacy_web);

    }

    @Override
    void initData() {


    }
}
