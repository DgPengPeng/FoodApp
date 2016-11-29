package com.dong.foodsect.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.foodsect.R;

/**
 * Created by dllo on 16/11/28.
 */
public class MySetDetailsActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout llBack;

    @Override
    int setLayout() {
        return R.layout.activity_my_set_details;
    }

    @Override
    void initView() {
        llBack = (LinearLayout) findViewById(R.id.ll_set_details);
        llBack.setOnClickListener(this);
    }

    @Override
    void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MySetDetailsActivity.this,MainActivity.class);
        intent.putExtra("id",2);
        startActivity(intent);
    }
}
