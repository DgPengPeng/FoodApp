package com.dong.foodsect.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dong.foodsect.R;
import com.dong.foodsect.activity.EvaluationDetailsActivity;
import com.dong.foodsect.activity.KnowledgeDetailsActivity;
import com.dong.foodsect.adapter.MyCollectDetailsListViewAdapter;
import com.dong.foodsect.volleydemo.CollectBean;
import com.dong.foodsect.volleydemo.DBTool;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/12/12.
 */
public class MyFoodFragment extends BaseFragment {

    private List<String> listNewLink;
    private List<String> listTitle;
    private List<CollectBean> list;
    private MyCollectDetailsListViewAdapter myCollectDetailsListViewAdapter;
    private ListView listView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my_article;
    }

    @Override
    protected void initView(View view) {
        listView = bindView(R.id.my_food_ls);
        //myCollectDetailsListViewAdapter = new MyCollectDetailsListViewAdapter(mcontext);

    }

    @Override
    void initData() {
//        list = DBTool.getInstance().queryUrlAll();
//        listNewLink = new ArrayList<>();
//        listTitle = new ArrayList<>();
//        if (list != null) {
//            for (int i = list.size(); i > 0; i--) {
//                listNewLink.add(list.get(i - 1).getUrl());
//                listTitle .add(list.get(i - 1).getTitle());
//
//            }
//        }
//        myCollectDetailsListViewAdapter.setClear();
//        myCollectDetailsListViewAdapter.setData(listTitle);
//        listView.setAdapter(myCollectDetailsListViewAdapter);
//
//
//        listView.setOnItemClickListener(this);

    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(getContext(), KnowledgeDetailsActivity.class);
//        intent.putExtra("url", listNewLink.get(i));
//        startActivity(intent);
    //}
}
