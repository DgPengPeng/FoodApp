package com.dong.foodsect.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.dong.foodsect.R;
import com.dong.foodsect.fragment.MyFragment;

/**
 * Created by dllo on 16/11/25.
 *
 * 这是我的_登录详情页
 */ 
public class MyDetailsActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    @Override
    int setLayout() {
        return R.layout.activity_my_details;
    }

    @Override
    void initView() {

        linearLayout = (LinearLayout) findViewById(R.id.my_details_ll);
        linearLayout.setOnClickListener(this);
    }

    @Override
    void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MyDetailsActivity.this,MainActivity.class);
        intent.putExtra("id",1);
        startActivity(intent);

    }
}
