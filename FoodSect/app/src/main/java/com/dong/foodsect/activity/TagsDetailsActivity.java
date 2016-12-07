package com.dong.foodsect.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.Tools.AllUrl;
import com.dong.foodsect.Tools.GroupPopClick;
import com.dong.foodsect.adapter.LibraryDetailsAdapter;
import com.dong.foodsect.adapter.TagsPopAdapter;
import com.dong.foodsect.bean.GroupPopBean;
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
public class TagsDetailsActivity extends BaseActivity implements View.OnClickListener, GroupPopClick {
    private PullToRefreshListView pullToRefreshListView;
    private LibraryDetailsAdapter libraryDetailsAdapter;
    private List<LibraryDetailsBean.FoodsBean> data;
    private int i = 1;
    private ImageView backIv;
    private TextView showTv;

    private PopupWindow popupWindow;
    private RecyclerView recyclerView;
    private String url = AllUrl.NUTRIENT;
    private RelativeLayout relativeLayout;
    private TagsPopAdapter tagsPopAdapter;
    private ImageView popIv;
    private String kind;
    private String id;
    private int pos;


    @Override
    int setLayout() {
        return R.layout.activity_details_tags;
    }

    @Override
    void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.library_tags_listview);
        backIv = bindView(R.id.iv_tags_details_back);
        popIv = bindView(R.id.iv_tags_pop_down);
        relativeLayout = bindView(R.id.rl_tags);
        showTv = bindView(R.id.tv_tags_top);
        getPopClick();
        backIv.setOnClickListener(this);
        libraryDetailsAdapter = new LibraryDetailsAdapter(this);
        data = new ArrayList<>();
        tagsPopAdapter = new TagsPopAdapter(this);
    }

    private void getPopClick() {
        popIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // popupWindow.showAsDropDown(relativeLayout);
                popupWindow.setAnimationStyle(R.style.popWindow_animation);
                //popupWindow.showAsDropDown(relativeLayout);
                popupWindow.showAtLocation(relativeLayout, Gravity.TOP, 0, (int) getResources().getDimension(R.dimen.pop_height));
            }
        });
    }

    @Override
    void initData() {
        getNewGroupDetailsData();
        initPop();
    }

    public void initPop(){
        final View view = LayoutInflater.from(this).inflate(R.layout.group_pop_recycleview, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.group_pop_recycleview);
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        NetHelper.MyRequest(url, GroupPopBean.class, new NetListener<GroupPopBean>() {
            @Override
            public void successListener(GroupPopBean response) {
                List<GroupPopBean.TypesBean> data = response.getTypes();
                tagsPopAdapter.setData(data);
                recyclerView.setAdapter(tagsPopAdapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(TagsDetailsActivity.this, 3, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);
                popupWindow.setContentView(view);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        tagsPopAdapter.setGroupPopClick(this);
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

    @Override
    public void PopClick(String posPop) {
        String url = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + AllUrl.FOOD_NUTRITION +
                posPop + AllUrl.FOOD_NUTRITION_CETER + pos + AllUrl.FOOD_NUTRITION_TAIL;
        NetHelper.MyRequest(url, LibraryDetailsBean.class, new NetListener<LibraryDetailsBean>() {
            @Override
            public void successListener(LibraryDetailsBean response) {
                libraryDetailsAdapter.getClear();
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
    private void getGroupDetailsData(String TagsUrl) {

        NetHelper.MyRequest(TagsUrl, LibraryDetailsBean.class, new NetListener<LibraryDetailsBean>() {
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

    //网址2
    public String getUrl(int i) {
        Intent intent = getIntent();
        String TagsUrl = intent.getStringExtra("urlTags");
        String name = intent.getStringExtra("name");
        showTv.setText(name);
        kind = intent.getStringExtra("kind");
        id = intent.getStringExtra("id");
        pos = intent.getIntExtra("pos", 0);
        String newTagsUrl = TagsUrl + i + AllUrl.FOOD_FOUR;
        return newTagsUrl;
    }

//


}
