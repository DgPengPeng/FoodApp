package com.dong.foodsect.fragment;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.tools.HomePageClick;
import com.dong.foodsect.activity.HomeDetailsWebViewActivity;
import com.dong.foodsect.activity.HomePageDetailsActivity;
import com.dong.foodsect.adapter.HomePageAdapter;
import com.dong.foodsect.bean.HomePageBean;
import com.dong.foodsect.tools.AllUrl;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 * <p>
 * 这是 首页 Fragment
 */
public class HomePageFragment extends BaseFragment implements HomePageClick {

    private LRecyclerView homePageRv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private HomePageAdapter homePageAdapter;
    private HomePageBean bean;

    private int i = 1;
    private List<HomePageBean.FeedsBean> data;

    @Override
    protected int setLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView(View view) {
//        homePageRv = (RecyclerView) view.findViewById(R.id.rv_homepage);
        homePageRv = (LRecyclerView) view.findViewById(R.id.homepage_fragment_rv);
        homePageAdapter = new HomePageAdapter(mcontext);

    }

    @Override
    void initData() {

        getContent(getUrl(1));
        // 系统的适配器
        lRecyclerViewAdapter = new LRecyclerViewAdapter(homePageAdapter);
        homePageRv.setAdapter(lRecyclerViewAdapter);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);
        homePageRv.setLayoutManager(manager);


        // 刷新
        homePageRv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                homePageAdapter.setClear();
                getContent(getUrl(i));
                homePageRv.refreshComplete();
                lRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        // 加载
        homePageRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                i = i + 1;
                getContent(getUrl(i));
            }
        });


        homePageAdapter.setHomePageClick(this);
    }


    // 网址
    public String getUrl(int i) {
        String url = AllUrl.EVA_HEAD + i + AllUrl.HOME_PAGE_FOOTER;
        return url;
    }


    private void getContent(String url) {
        NetHelper.MyRequest(url, HomePageBean.class, new NetListener<HomePageBean>() {
            @Override
            public void successListener(HomePageBean response) {
                List<HomePageBean.FeedsBean> mid = response.getFeeds();
                if (data == null){
                    data = mid;
                }else {
                    for (int i = 0; i < mid.size(); i++) {
                        data.add(mid.get(i));
                    }
                }
                homePageAdapter.setData(data);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    @Override
    public void HomePageListenter(int pos, int i) {
        if (i == 6) {
            Intent intent = new Intent(getContext(), HomeDetailsWebViewActivity.class);
            intent.putExtra("link", data.get(pos).getLink());
            startActivity(intent);
        } else if (i == 5) {
            Intent intent = new Intent(getContext(), HomePageDetailsActivity.class);
            intent.putExtra("card",data.get(pos).getCard_image());
            intent.putExtra("publisher",data.get(pos).getPublisher());
            intent.putExtra("publisher_avatar",data.get(pos).getPublisher_avatar());
            intent.putExtra("like_ct",data.get(pos).getLike_ct() + "");
            startActivity(intent);
        }
    }
//    private void getContent() {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                bean = gson.fromJson(response, HomePageBean.class);
//                List<HomePageBean.FeedsBean> data = bean.getFeeds();
//                homePageAdapter.setData(data);
//                homePageRv.setAdapter(homePageAdapter);
//                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);
//                homePageRv.setLayoutManager(manager);
//
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(stringRequest);
//    }
}
