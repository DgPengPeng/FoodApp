package com.dong.foodsect.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dong.foodsect.R;
import com.dong.foodsect.activity.ChainDetailsActivity;
import com.dong.foodsect.activity.GroupDetailsActivity;
import com.dong.foodsect.activity.TagsDetailsActivity;
import com.dong.foodsect.adapter.LibraryAdapter;
import com.dong.foodsect.adapter.LibraryChanAdapter;
import com.dong.foodsect.adapter.LibraryTagsAdapter;
import com.dong.foodsect.bean.LibraryBean;
import com.dong.foodsect.Tools.AllUrl;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/11/22.
 * 这是 食物百科  Fragment
 */
public class LibraryFragment extends BaseFragment {

    //    private LibraryAdapter libraryAdapter;
    private GridView foodGv, tagsGv, chainGv;
    private String url = AllUrl.FOODENCYCLOPEDIA;
    private List<LibraryBean.GroupBean.CategoriesBean> data;
    private List<LibraryBean.GroupBean.CategoriesBean> dataTags;
    private List<LibraryBean.GroupBean.CategoriesBean> dataChan;
    private LibraryBean libBean = new LibraryBean();

    private LibraryAdapter libraryAdapter;
    private LibraryTagsAdapter libraryTagsAdapter;
    private LibraryChanAdapter libraryChanAdapter;
    private String kind;
    private String id;
    private String kind1;
    private String id1;

    // 可以传值,相当 fragment 复用
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
        getGirdViewLis();

    }





    private void getGirdViewLis() {
        NetHelper.MyRequest(url, LibraryBean.class, new NetListener<LibraryBean>() {
            @Override
            public void successListener(final LibraryBean response) {

                data = response.getGroup().get(0).getCategories();
                dataTags = response.getGroup().get(1).getCategories();
                dataChan= response.getGroup().get(2).getCategories();

                libraryAdapter = new LibraryAdapter(mcontext);
                libraryTagsAdapter = new LibraryTagsAdapter(mcontext);
                libraryChanAdapter = new LibraryChanAdapter(mcontext);

                libraryAdapter.setDatas(data);
                libraryTagsAdapter.setDatas(dataTags);
                libraryChanAdapter.setDatas(dataChan);

                foodGv.setAdapter(libraryAdapter);
                tagsGv.setAdapter(libraryTagsAdapter);
                chainGv.setAdapter(libraryChanAdapter);

                foodGv(response);
                tagsGv(response);
                chainGv(response);


            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void chainGv(final LibraryBean response) {
        chainGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChainDetailsActivity.class);
                kind = response.getGroup().get(2).getKind();
                id = dataChan.get(i).getId() + "";
                Log.d("xxx", id);
//                intent.putExtra("kind",kind);
//                intent.putExtra("id",id);
                String urlChan = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + AllUrl.FOOD_THREE ;
                intent.putExtra("urlChan",urlChan);
                startActivity(intent);
            }
        });
    }

    private void tagsGv(final LibraryBean response) {
        tagsGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), TagsDetailsActivity.class);
                kind = response.getGroup().get(1).getKind();
                id = dataTags.get(i).getId() + "";

                String urlTags = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + AllUrl.FOOD_THREE ;
                Log.d("qwe", urlTags);
                intent.putExtra("urlTags",urlTags);
//                intent.putExtra("kind",kind);
//                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void foodGv(final LibraryBean response) {
        foodGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), GroupDetailsActivity.class);
                kind = response.getGroup().get(0).getKind();
                id = data.get(i).getId() + "";
                Log.d("xxx", id);
//                intent.putExtra("kind",kind);
//                intent.putExtra("id",id);
                String urlGroup = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + AllUrl.FOOD_THREE ;
                intent.putExtra("urlGroup",urlGroup);
                startActivity(intent);
            }
        });
    }


}
