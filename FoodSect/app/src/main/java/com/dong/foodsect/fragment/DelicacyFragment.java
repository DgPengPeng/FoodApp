package com.dong.foodsect.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.activity.DelicacyDetailsActivity;
import com.dong.foodsect.adapter.DelicacyAdapter;
import com.dong.foodsect.bean.DelicacyBean;
import com.dong.foodsect.tools.AllUrl;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 * <p/>
 * 这是 美食 Fragment
 */
public class DelicacyFragment extends BaseFragment {
    //private ListView listView;
    private PullToRefreshListView pullToRefreshListView;
    private DelicacyAdapter delicacyAdapter;


    private int i = 1;
    private List<DelicacyBean.FeedsBean> data;

    @Override
    protected int setLayout() {
        return R.layout.fragment_delicacy;
    }

    @Override
    protected void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.delicacy_listview);
        delicacyAdapter = new DelicacyAdapter(mcontext);

    }


    @Override
    void initData() {
        //getContent();
        getNewListView();
    }

    // 网址
    public String getUrl(int i) {
        String url = AllUrl.EVA_HEAD + i + AllUrl.DEL_FOOTER;
        return url;
    }

    private void getContent(String url) {
        NetHelper.MyRequest(url, DelicacyBean.class, new NetListener<DelicacyBean>() {
            @Override
            public void successListener(DelicacyBean response) {
                List<DelicacyBean.FeedsBean> mid = response.getFeeds();
                if (data == null) {
                    data = mid;
                } else {
                    for (int i = 0; i < mid.size(); i++) {
                        data.add(mid.get(i));
                    }
                }
                delicacyAdapter.setData(data);
                Log.d("zzz", "data:" + data);

                pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(), DelicacyDetailsActivity.class);
                        String url = data.get(i - 1).getLink();
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

    private void getNewListView() {

        getContent(getUrl(1));
        pullToRefreshListView.setAdapter(delicacyAdapter);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 刷新
                new RefreshData().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                // 加载
                new LoadData().execute();
            }
        });

    }

    // 刷新
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
            delicacyAdapter.clearData();
            getContent(getUrl(integer));
            delicacyAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    // 加载
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
            getContent(getUrl(integer));
            delicacyAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }


//    private void getContent() {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                DelicacyBean delicacyBean = gson.fromJson(response,DelicacyBean.class);
//                List<DelicacyBean.FeedsBean> data = delicacyBean.getFeeds();
//                delicacyAdapter.setData(data);
//                Log.d("zzz", "data:" + data);
//                listView.setAdapter(delicacyAdapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(stringRequest);
//    }
}
