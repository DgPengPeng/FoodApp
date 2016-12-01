package com.dong.foodsect.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.Tools.AllUrl;
import com.dong.foodsect.adapter.LibraryDetailsAdapter;
import com.dong.foodsect.bean.LibraryBean;
import com.dong.foodsect.bean.LibraryDetailsBean;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/1.
 */
public class GroupDetailsActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView pullToRefreshListView;
    private LibraryDetailsAdapter libraryDetailsAdapter;
    private List<LibraryDetailsBean.FoodsBean> data;
    private int i = 1;
    private ImageView backI;


    @Override
    int setLayout() {
        return R.layout.activity_details_group;
    }

    @Override
    void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.library_group_listview);
        backI = bindView(R.id.iv_group_details_back);
        backI.setOnClickListener(this);
        libraryDetailsAdapter = new LibraryDetailsAdapter(this);
        data = new ArrayList<>();
    }

    @Override
    void initData() {
        getNewGroupDetailsData();

    }

    private void getNewGroupDetailsData() {
        getGroupDetailsData(getUrl(1));
        pullToRefreshListView.setAdapter(libraryDetailsAdapter);
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

    @Override
    public void onClick(View view) {
        finish();
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
            libraryDetailsAdapter.getClear();
            getGroupDetailsData(getUrl(integer));
            libraryDetailsAdapter.notifyDataSetChanged();
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
            getGroupDetailsData(getUrl(integer));
            libraryDetailsAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

        }
    }

    //  食物分类
    private void getGroupDetailsData(String GroupUrl) {

        NetHelper.MyRequest(GroupUrl, LibraryDetailsBean.class, new NetListener<LibraryDetailsBean>() {
            @Override
            public void successListener(LibraryDetailsBean response) {
                List<LibraryDetailsBean.FoodsBean> mid = response.getFoods();
                if (data == null) {
                    data = mid;
                } else {
                    for (int i = 0; i < mid.size(); i++) {
                        data.add(mid.get(i));
                    }
                }
                libraryDetailsAdapter.setData(data);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    //网址1
    public String getUrl(int i) {
        Intent intent = getIntent();
        String urlGroup = intent.getStringExtra("urlGroup");
//        String kind = intent.getStringExtra("kind");
//        String id = intent.getStringExtra("id") ;
        String newUrlGroup = urlGroup + i + AllUrl.FOOD_FOUR;
        //String newUrlGroup = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + i + AllUrl.FOOD_FOUR;

        return newUrlGroup;
    }


}
