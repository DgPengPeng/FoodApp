package com.dong.foodsect.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.tools.AllUrl;
import com.dong.foodsect.adapter.SearchGvDetailsAdapter;
import com.dong.foodsect.bean.SearchGvDetailsBean;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */
public class SearchDetailsGridViewActivity extends BaseActivity implements View.OnClickListener {

    private EditText showEt;
    private int page;
    private String name;
    private SearchGvDetailsAdapter searchGvDetailsAdapter;
    private PullToRefreshListView pullToRefreshListView;
    private List<SearchGvDetailsBean.ItemsBean> data;
    private int i = 1;
    private String newUrl;
    private ImageView backIv,clearIv;

    @Override
    int setLayout() {
        return R.layout.activity_gridview_search_details;
    }

    @Override
    void initView() {
        showEt = bindView(R.id.et_search_show);
        pullToRefreshListView = bindView(R.id.gv_search_listview);
        searchGvDetailsAdapter = new SearchGvDetailsAdapter(this);
        backIv = bindView(R.id.iv_search_details_back);
        clearIv = bindView(R.id.iv_gv_search_clear);
        clearIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    void initData() {
        getNewGvData();
    }

    private void getNewGvData() {
        getNewData(getSearchUrl(1));
        pullToRefreshListView.setAdapter(searchGvDetailsAdapter);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new RefreshData().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new LoadData().execute();
            }
        });
    }


    // 解析 GridView详情页 网址
    private void getNewData(String url) {
        NetHelper.MyRequest(url, SearchGvDetailsBean.class, new NetListener<SearchGvDetailsBean>() {
            @Override
            public void successListener(SearchGvDetailsBean response) {
                List<SearchGvDetailsBean.ItemsBean> mid = response.getItems();

                if (data == null){
                    data = mid;

                }else {
                    for (int i = 0; i < mid.size(); i++) {
                        data.add(mid.get(i));
                    }
                }
                searchGvDetailsAdapter.setData(data);


            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    // 搜索详情页 返回
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_details_back:
//                if (showEt != null){
//                    Intent intent = new Intent("com.dong.foodsect.activity.MY_BR");
//                    intent.putExtra("key","新数据");
//                    sendBroadcast(intent);
//                }
                Intent intent1 = new Intent(SearchDetailsGridViewActivity.this,MainActivity.class);
                intent1.putExtra("id",3);
                startActivity(intent1);
                break;
            case R.id.iv_gv_search_clear:
                // 发送广播

                if (showEt != null){
                    Intent intent = new Intent("com.dong.foodsect.activity.MY_BR");
                    intent.putExtra("key","新数据");
                    sendBroadcast(intent);
                }
                finish();
                break;
        }
    }

    // 刷新
    private class RefreshData extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(2000);
                i = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            searchGvDetailsAdapter.getClear();
            getNewData(getSearchUrl(integer));
            searchGvDetailsAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    // 加载
    private class LoadData extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(2000);
                i = i + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            getNewData(getSearchUrl(integer));
            searchGvDetailsAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    public String getSearchUrl(int i) {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        showEt.setText(name);
        page = intent.getIntExtra("page",1);
        newUrl = AllUrl.SEARCH_HEAD + i + AllUrl.SEARCH_MID + name;
        return newUrl;
    }
}
