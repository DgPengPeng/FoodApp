package com.dong.foodsect.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dong.foodsect.R;
import com.dong.foodsect.adapter.LibraryAdapter;
import com.dong.foodsect.bean.LibraryBean;
import com.dong.foodsect.Tools.AllUrl;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/11/22.
 * 这是 食物百科  Fragment
 */
public class LibraryFragment extends BaseFragment{

//    private LibraryAdapter libraryAdapter;
    private GridView foodGv,tagsGv,chainGv;
    private String url = AllUrl.FOODENCYCLOPEDIA;

    // 可以传值,相当 new Fragment
    public static LibraryFragment newInstance() {

        Bundle args = new Bundle();

        LibraryFragment fragment = new LibraryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initView(View view) {
        foodGv = (GridView) view.findViewById(R.id.library_food_sort);
        tagsGv = (GridView) view.findViewById(R.id.library_tags_brand);
        chainGv = (GridView) view.findViewById(R.id.library_chain_drink);

    }

    @Override
    void initData() {

        RequestQueue queue = Volley.newRequestQueue(mcontext);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                LibraryBean bean = gson.fromJson(response,LibraryBean.class);
                List<LibraryBean.GroupBean.CategoriesBean> data = bean.getGroup().get(0).getCategories();
                LibraryAdapter libraryAdapter = new LibraryAdapter(mcontext);
                libraryAdapter.setDatas(data);
                foodGv.setAdapter(libraryAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);




        RequestQueue queue1 = Volley.newRequestQueue(mcontext);
        StringRequest stringRequest1 = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                LibraryBean bean = gson.fromJson(response,LibraryBean.class);
                List<LibraryBean.GroupBean.CategoriesBean> data = bean.getGroup().get(1).getCategories();
                LibraryAdapter tagsAdapter = new LibraryAdapter(mcontext);
                tagsAdapter.setDatas(data);
                tagsGv.setAdapter(tagsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue1.add(stringRequest1);



        RequestQueue queue2 = Volley.newRequestQueue(mcontext);
        StringRequest stringRequest2 = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                LibraryBean bean = gson.fromJson(response,LibraryBean.class);
                List<LibraryBean.GroupBean.CategoriesBean> data = bean.getGroup().get(2).getCategories();
                LibraryAdapter chanAdapter = new LibraryAdapter(mcontext);
                chanAdapter.setDatas(data);
                chainGv.setAdapter(chanAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue2.add(stringRequest2);
    }


}
