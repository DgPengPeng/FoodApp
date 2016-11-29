package com.dong.foodsect.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dong.foodsect.R;
import com.dong.foodsect.adapter.ShopAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/22.
 *
 * 这是 逛吃 Fragment
 */
public class ShopFragment extends BaseFragment {
    private ViewPager shopVp;
    private TabLayout shopTab;
    private ShopAdapter shopAdapter;
    private Context context;
    private ArrayList<Fragment>fragments;

    public static ShopFragment newInstance() {

        Bundle args = new Bundle();

        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View view) {
        shopVp = (ViewPager) view.findViewById(R.id.vp_shop);
        shopTab = (TabLayout) view.findViewById(R.id.tab_shop);
        shopAdapter = new ShopAdapter(getChildFragmentManager(),context);
        fragments = new ArrayList<>();


    }

    @Override
    void initData() {
        fragments.add(new HomePageFragment());
        fragments.add(new EvaluationFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new DelicacyFragment());
        shopAdapter.setFragments(fragments);
        shopVp.setAdapter(shopAdapter);
        shopTab.setupWithViewPager(shopVp);

        shopTab.setTabTextColors(Color.BLACK,Color.argb(200,255,1285,0));
    }
}
