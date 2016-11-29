package com.dong.foodsect.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.activity.MyDetailsActivity;
import com.dong.foodsect.activity.MySetDetailsActivity;

/**
 * Created by dllo on 16/11/22.
 * <p>
 * 这是 我的  Fragment
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private TextView myTv;
    private ImageView myIv;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        myTv = (TextView) view.findViewById(R.id.tv_my_head_login);
        myIv = (ImageView) view.findViewById(R.id.my_set);
        myTv.setOnClickListener(this);
        myIv.setOnClickListener(this);

    }

    @Override
    void initData() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_head_login:
                Intent intent = new Intent(mcontext, MyDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_set:
                Intent intent1 = new Intent(mcontext, MySetDetailsActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
