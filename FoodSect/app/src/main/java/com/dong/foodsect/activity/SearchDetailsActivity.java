package com.dong.foodsect.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.Tools.AllUrl;
import com.dong.foodsect.adapter.SearchGvAdapter;
import com.dong.foodsect.adapter.SearchRvAdapter;
import com.dong.foodsect.bean.SearchRvBean;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailsActivity extends BaseActivity{

   // private RecyclerView recyclerView;
   private GridView gridView;
    private String url = AllUrl.SEARCH;
    private List<String> data;
    //private SearchRvAdapter searchRvAdapter;
    private SearchGvAdapter searchGvAdapter;
    @Override
    int setLayout() {
        return R.layout.activity_search_details;
    }

    @Override
    void initView() {
        //recyclerView = bindView(R.id.search_details_rv);
        gridView = bindView(R.id.search_details_gv);
        //searchRvAdapter = new SearchRvAdapter(this);
        searchGvAdapter = new SearchGvAdapter(this);
        data = new ArrayList<>();
    }

    @Override
    void initData() {
//        NetHelper.MyRequest(url, SearchRvBean.class, new NetListener<SearchRvBean>() {
//            @Override
//            public void successListener(SearchRvBean response) {
//                data = response.getKeywords();
//                searchRvAdapter.setData(data);
//                recyclerView.setAdapter(searchRvAdapter);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchDetailsActivity.this,2, LinearLayoutManager.VERTICAL,false);
//                recyclerView.setLayoutManager(gridLayoutManager);
//            }
//
//            @Override
//            public void errorListener(VolleyError error) {
//
//            }
//        });
        getGvData();
    }

    private void getGvData() {
        NetHelper.MyRequest(url, SearchRvBean.class, new NetListener<SearchRvBean>() {
            @Override
            public void successListener(SearchRvBean response) {
                data = response.getKeywords();
                searchGvAdapter.setData(data);
                gridView.setAdapter(searchGvAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
