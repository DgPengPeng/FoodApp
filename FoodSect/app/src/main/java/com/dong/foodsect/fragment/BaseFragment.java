package com.dong.foodsect.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/11/22.
 *
 * 这是 Fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    Context mcontext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    // 加载布局
    protected abstract int setLayout();
    // 初始化
    protected abstract void initView(View view);
    // 逻辑代码
    abstract void initData();
    // findViewById
    public <T extends View> T bindView(int id){
        return (T)getView().findViewById(id);
    }
}
