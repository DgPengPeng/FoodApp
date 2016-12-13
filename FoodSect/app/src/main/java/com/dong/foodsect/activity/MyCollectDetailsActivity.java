package com.dong.foodsect.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dong.foodsect.R;
import com.dong.foodsect.adapter.MyCollectAdapter;
import com.dong.foodsect.adapter.ShopAdapter;
import com.dong.foodsect.fragment.DelicacyFragment;
import com.dong.foodsect.fragment.EvaluationFragment;
import com.dong.foodsect.fragment.HomePageFragment;
import com.dong.foodsect.fragment.KnowledgeFragment;
import com.dong.foodsect.fragment.LibraryFragment;
import com.dong.foodsect.fragment.MyArticleFragment;
import com.dong.foodsect.fragment.MyFoodFragment;
import com.dong.foodsect.fragment.MyFragment;
import com.dong.foodsect.fragment.ShopFragment;

import java.util.ArrayList;


public class MyCollectDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager myDetVp;
    private TabLayout myDetTab;
    private MyCollectAdapter myCollectAdapter;
    private ArrayList<Fragment> fragments;
    private ImageView backIv;


    @Override
    int setLayout() {
        return R.layout.activity_my_collect_details;
    }

    @Override
    void initView() {
        myDetVp = bindView(R.id.vp_my_details);
        myDetTab = bindView(R.id.tab_my_details);
        backIv = bindView(R.id.my_collect_back);
        backIv.setOnClickListener(this);
        myCollectAdapter = new MyCollectAdapter(getSupportFragmentManager(),this);
        fragments = new ArrayList<>();

    }

    @Override
    void initData() {

        fragments.add(new MyArticleFragment());
        fragments.add(new MyFoodFragment());
        myCollectAdapter.setFragments(fragments);
        myDetVp.setAdapter(myCollectAdapter);
        myDetTab.setupWithViewPager(myDetVp);

        myDetTab.setTabTextColors(Color.BLACK,Color.argb(200,255,1285,0));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_collect_back:
                finish();
                break;
        }
    }
}
