package com.dong.foodsect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.squareup.picasso.Picasso;


public class HomePageDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView avaIv, cardIv;
    private TextView pubTv, likeTv;
    private ImageView backIv;

    @Override
    int setLayout() {
        return R.layout.activity_home_page_details;
    }

    @Override
    void initView() {

        avaIv = bindView(R.id.home_page_ava);
        cardIv = bindView(R.id.home_page_card_image);
        pubTv = bindView(R.id.home_page_publisher);
        likeTv = bindView(R.id.home_page_like_ct);
        backIv = bindView(R.id.home_page_back);
        backIv.setOnClickListener(this);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        intent.getStringExtra("publisher_avatar");
        Picasso.with(this).load(intent.getStringExtra("card")).into(cardIv);
        Picasso.with(this).load(intent.getStringExtra("publisher_avatar")).into(avaIv);
        pubTv.setText(intent.getStringExtra("publisher"));
        likeTv.setText(intent.getStringExtra("like_ct"));


    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
