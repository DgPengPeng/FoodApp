package com.dong.foodsect.fragment;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dong.foodsect.R;
import com.dong.foodsect.adapter.KnowledgeAdapter;
import com.dong.foodsect.bean.KnowleageBean;
import com.dong.foodsect.urltool.AllUrl;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 * <p/>
 * 这是 知识 Fragment
 */
public class KnowledgeFragment extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    private KnowledgeAdapter knowledgeAdapter;

    private int i = 1;

    @Override
    protected int setLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView(View view) {

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.knowledge_listview);
        knowledgeAdapter = new KnowledgeAdapter(mcontext);
    }

    @Override
    void initData() {

        getNewListView();
    }

    private void getNewListView() {
        getListView(getUrl(1));
        pullToRefreshListView.setAdapter(knowledgeAdapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 刷新方法写在这里
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 刷新 异步

                new RefreshData().execute();
            }

            // 加载方法写这里
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 加载  异步
                new LoadData().execute();
            }
        });
    }

    // 刷新
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
            getListView(getUrl(integer));
            knowledgeAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();


        }
    }

    // 加载
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
            knowledgeAdapter.setClear();
            getListView(getUrl(integer));
            knowledgeAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    private String getUrl(int i) {
        String url = AllUrl.EVA_HEAD + i + AllUrl.KNOW_FOOTER;
        return url;
    }

    private void getListView(String url){
        NetHelper.MyRequest(url, KnowleageBean.class, new NetListener<KnowleageBean>() {
            @Override
            public void successListener(KnowleageBean response) {
                List<KnowleageBean.FeedsBean> data = response.getFeeds();
                knowledgeAdapter.setData(data);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
