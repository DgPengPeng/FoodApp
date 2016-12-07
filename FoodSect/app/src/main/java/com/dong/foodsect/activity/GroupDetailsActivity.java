package com.dong.foodsect.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.Tools.AllUrl;
import com.dong.foodsect.Tools.GroupPopClick;
import com.dong.foodsect.adapter.GroupPopAdapter;
import com.dong.foodsect.adapter.GroupPopListViewAdapter;
import com.dong.foodsect.adapter.LibraryDetailsAdapter;
import com.dong.foodsect.bean.GroupPopBean;
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
public class GroupDetailsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, GroupPopClick {
    private PullToRefreshListView pullToRefreshListView;
    private LibraryDetailsAdapter libraryDetailsAdapter;
    private List<LibraryDetailsBean.FoodsBean> data;
    private List<GroupPopBean.TypesBean> datas;
    private int i = 1;
    private ImageView backI;
    private ImageView downIv, upDownIv;
    private TextView showTv;

    // pop
    private RecyclerView recyclerView;
    private GroupPopAdapter groupPopAdapter;
    private PopupWindow popupWindow;
    private PopupWindow popupWindowUp;
    private String url = AllUrl.NUTRIENT;
    private String popUrl = AllUrl.FOODENCYCLOPEDIA;
    private RelativeLayout relativeLayout;
    private GroupPopListViewAdapter groupPopListViewAdapter;
    private ListView mlistView;
    private RelativeLayout mRelativeLayout;
    private List<String> popData;
    private int count;
    private String kind;
    private int pos;
    private String newUrlGroup;
    private String id;
    private LibraryBean libraryBean;
    private View listView;
    private List<Integer> lvIdAll;
    private int lvId;
    private RelativeLayout relativeLayoutPop;
    private RelativeLayout relativeLayoutPopRv;


    @Override
    int setLayout() {
        return R.layout.activity_details_group;
    }

    @Override
    void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.library_group_listview);
        backI = bindView(R.id.iv_group_details_back);
        //downIv = bindView(R.id.iv_group_pop_down);
        //upDownIv = bindView(R.id.iv_library_group);
        relativeLayoutPop = bindView(R.id.rl__library_group);
        relativeLayoutPopRv = bindView(R.id.details_group_rel);
        showTv = bindView(R.id.tv_group_top);
        relativeLayout = bindView(R.id.details_group_rel);
        mRelativeLayout = bindView(R.id.first_pop_down_rl);
        //downIv.setOnClickListener(this);
        //upDownIv.setOnClickListener(this);
        relativeLayoutPop.setOnClickListener(this);
        relativeLayoutPopRv.setOnClickListener(this);
        backI.setOnClickListener(this);
        groupPopAdapter = new GroupPopAdapter(this);
        groupPopListViewAdapter = new GroupPopListViewAdapter(this);

        libraryDetailsAdapter = new LibraryDetailsAdapter(this);
        data = new ArrayList<>();
        popData = new ArrayList<>();
    }

    // RecyclerView 的 pop
    public void initPopup() {
        final View view = LayoutInflater.from(this).inflate(R.layout.group_pop_recycleview, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.group_pop_recycleview);
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        NetHelper.MyRequest(url, GroupPopBean.class, new NetListener<GroupPopBean>() {
            @Override
            public void successListener(GroupPopBean response) {
                List<GroupPopBean.TypesBean> data = response.getTypes();
                groupPopAdapter.setData(data);
                recyclerView.setAdapter(groupPopAdapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(GroupDetailsActivity.this, 3, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);
                popupWindow.setContentView(view);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

       // popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        // 点击外面让popup消失
        //popupWindow.setOutsideTouchable(true);

        // 点击事件
        groupPopAdapter.setGroupPopClick(this);

    }

    // ListView 的 pop
    public void initPopFirstUp() {
        listView = LayoutInflater.from(this).inflate(R.layout.group_pop_listview, null);
        mlistView = (ListView) listView.findViewById(R.id.group_pop_listview);
        popupWindowUp = new PopupWindow(this);
        popupWindowUp.setWidth(170);
        popupWindowUp.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        NetHelper.MyRequest(popUrl, LibraryBean.class, new NetListener<LibraryBean>() {
            @Override
            public void successListener(LibraryBean response) {
                libraryBean = new LibraryBean();
                libraryBean = response;
                for (int i = 0; i < count; i++) {
                    popData.add(response.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i).getName());
                }
                popData.add((i - 1), "全部");
                groupPopListViewAdapter.setData(popData);
                mlistView.setAdapter(groupPopListViewAdapter);
                popupWindowUp.setContentView(listView);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        // 点击外边 pop 消失
        popupWindowUp.setTouchable(true);
        popupWindowUp.setFocusable(true);
        popupWindowUp.setOutsideTouchable(true);


//
//        // listView 行点击
        mlistView.setOnItemClickListener(this);
    }


    @Override
    void initData() {
        getNewGroupDetailsData();
        initPopup();
        initPopFirstUp();
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
        switch (view.getId()) {
            case R.id.iv_group_details_back:
                finish();
                break;
            // 弹出pop
            case R.id.details_group_rel:
                //动画
                popupWindow.setAnimationStyle(R.style.popWindow_animation);
                //popupWindow.showAsDropDown(relativeLayout);
                popupWindow.showAtLocation(relativeLayout, Gravity.TOP, 0, (int) getResources().getDimension(R.dimen.pop_height));
                break;
            // 上面的pop
            case R.id.rl__library_group:
                if (!popupWindowUp.isShowing()) {
                    popupWindowUp.showAsDropDown(mRelativeLayout, 625, 0);
                } else {
                    popupWindowUp.dismiss();
                }
                break;
        }

    }

    // listView 行点击
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            libraryDetailsAdapter.getClear();
            getGroupDetailsData(getUrl(1));
        } else {
            lvId = libraryBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i - 1).getId();
            Log.d("asd", "lvId:" + lvId);
            String lvPopUrl = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + AllUrl.FOOD_SUB_VALUE +
                    lvId + AllUrl.FOOD_SUB_VALUE_TWO + pos + AllUrl.FOOD_NUTRITION_TAIL;
            Log.d("mmm", lvPopUrl);
            libraryDetailsAdapter.getClear();
            NetHelper.MyRequest(lvPopUrl, LibraryDetailsBean.class, new NetListener<LibraryDetailsBean>() {
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


    }

    // RecyclerView pop 点击事件
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
        count = intent.getIntExtra("count", 0);
        kind = intent.getStringExtra("kind");
        pos = intent.getIntExtra("pos", 0);
        id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        showTv.setText(name);
        newUrlGroup = urlGroup + i + AllUrl.FOOD_FOUR;
        //String newUrlGroup = AllUrl.FOOD_ONE + kind + AllUrl.FOOD_TWO + id + i + AllUrl.FOOD_FOUR;
        return newUrlGroup;
    }




}
