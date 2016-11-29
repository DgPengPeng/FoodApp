package com.dong.foodsect.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dong.foodsect.R;
import com.dong.foodsect.activity.EvaluationDetailsActivity;
import com.dong.foodsect.adapter.EvaluationAdapter;
import com.dong.foodsect.bean.EvaluationBean;
import com.dong.foodsect.carousel.CarouselBean;
import com.dong.foodsect.carousel.GlideImageLoder;
import com.dong.foodsect.urltool.AllUrl;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 * <p>
 * 这是 测评 Fragment
 */
public class EvaluationFragment extends BaseFragment {


    // 上拉刷新的控件
    private PullToRefreshListView pullToRefreshListView;
    private EvaluationAdapter evaluationAdapter;

    // 轮播图
    //private List<String> data;
    private Banner banner;
    private View headView;


    private int i = 1;
    private List<EvaluationBean.FeedsBean> data;

    @Override
    protected int setLayout() {
        return R.layout.fragment_evaluation;
    }

    @Override
    protected void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.evaluation_listview);

        // headView = LayoutInflater.from(mcontext).inflate(R.layout.item_evaluation_head, null);
        evaluationAdapter = new EvaluationAdapter(mcontext);
        //data = new ArrayList<>();
        banner = (Banner) view.findViewById(R.id.banner);
    }

    @Override
    void initData() {
        //getHead();
        getNewListView();

//        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent intent = new Intent(getContext(), EvaluationDetailsActivity.class);
//                intent.putExtra("url",)
//            }
//        });
    }

    public String Url_all(int i) {
        String url = AllUrl.EVA_HEAD + i + AllUrl.EVA_FOOTER;
        return url;
    }

    private void getNewListView() {
        getListView(Url_all(1));
        pullToRefreshListView.setAdapter(evaluationAdapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 调用刷新异步任务
                new RefreshData().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 调用加载异步任务
                new LoadData().execute();


            }
        });
    }

    private void getListView(final String url) {
        NetHelper.MyRequest(url, EvaluationBean.class, new NetListener<EvaluationBean>() {
            @Override
            public void successListener(EvaluationBean response) {
                data = response.getFeeds();
                evaluationAdapter.setData(data);
                pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getContext(), EvaluationDetailsActivity.class);
                            String url = data.get(i).getLink();
                            intent.putExtra("url", url);
                            startActivity(intent);

                    }
                });

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


    /**
     * 加载异步任务
     */
    private class LoadData extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(1000);
                i = i + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            getListView(Url_all(integer));
            evaluationAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    /**
     * 刷新
     */

    private class RefreshData extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(1000);
                i = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            evaluationAdapter.Clear();
            getListView(Url_all(integer));
            evaluationAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();
        }
    }


    // 轮播图
//   private void getHead() {
//        listView.addHeaderView(headView);
//        String carouseUrl = AllUrl.CAROUSE;
//        RequestQueue requesQueue = Volley.newRequestQueue(mcontext);
//        StringRequest sr = new StringRequest(carouseUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                CarouselBean carouselBean = gson.fromJson(response, CarouselBean.class);
//                for (int i = 0; i < carouselBean.getData().getBanners().size(); i++) {
//                    data.add(carouselBean.getData().getBanners().get(i).getImage_url());
//                }
//                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//                banner.setImageLoader(new GlideImageLoder());
//                banner.setImages(data);
//                // 设置动画
//                banner.setBannerAnimation(Transformer.DepthPage);
//                banner.isAutoPlay(true);
//                banner.setDelayTime(2000);
//                banner.setIndicatorGravity(BannerConfig.CENTER);
//                banner.start();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("aaaa", "获取失败");
//            }
//        });
//        requesQueue.add(sr);
//    }


}
