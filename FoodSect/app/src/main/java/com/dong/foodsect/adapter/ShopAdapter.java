package com.dong.foodsect.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 *
 * 这是 逛吃 适配器
 */
public class ShopAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private Context context;


    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public ShopAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    private String []title = {"首页","评测","知识","美食"};


    public ShopAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
