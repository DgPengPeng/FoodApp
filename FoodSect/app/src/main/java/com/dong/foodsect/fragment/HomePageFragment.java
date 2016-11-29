package com.dong.foodsect.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dong.foodsect.R;
import com.dong.foodsect.adapter.HomePageAdapter;
import com.dong.foodsect.bean.HomePageBean;
import com.dong.foodsect.fragment.BaseFragment;
import com.dong.foodsect.urltool.AllUrl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 *
 * 这是 首页 Fragment
 */
public class HomePageFragment extends BaseFragment {

    private RecyclerView homePageRv;
    private HomePageAdapter homePageAdapter;
    private String url = AllUrl.HOME_PAGE;
    private HomePageBean bean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView(View view) {
        homePageRv = (RecyclerView) view.findViewById(R.id.rv_homepage);
        homePageAdapter = new HomePageAdapter(mcontext);

    }

    @Override
    void initData() {
        getContent();
    }

    private void getContent() {
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, HomePageBean.class);
                List<HomePageBean.FeedsBean> data = bean.getFeeds();
                homePageAdapter.setData(data);
                homePageRv.setAdapter(homePageAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);
                homePageRv.setLayoutManager(manager);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
