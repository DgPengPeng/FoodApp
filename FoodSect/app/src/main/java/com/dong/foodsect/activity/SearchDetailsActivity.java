package com.dong.foodsect.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.dong.foodsect.R;
import com.dong.foodsect.tools.AllUrl;
import com.dong.foodsect.tools.ClearEditText;
import com.dong.foodsect.tools.NoScrollListView;
import com.dong.foodsect.adapter.SearchDetailsListViewAdapter;
import com.dong.foodsect.adapter.SearchGvAdapter;
import com.dong.foodsect.bean.SearchRvBean;
import com.dong.foodsect.volleydemo.DBTool;
import com.dong.foodsect.volleydemo.NetHelper;
import com.dong.foodsect.volleydemo.NetListener;
import com.dong.foodsect.volleydemo.RecentSearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索详情页
 */
public class SearchDetailsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    //private RecyclerView recyclerView;
    private GridView gridView;
    private String url = AllUrl.SEARCH;
    private List<String> data;
    // private SearchRvAdapter searchRvAdapter;
    // gridView
    private SearchGvAdapter searchGvAdapter;
    private ImageView backIv, searchIv;
    private SearchRvBean searchRvBean;
    private ClearEditText clearEditText;
    private Toast toast;
    private String nameKey;
    //private ArrayList<String> listNewName;


    // listView 适配器
    private SearchDetailsListViewAdapter searchDetailsListViewAdapter;
    private NoScrollListView listView;


    //private ListView showLv;
//    private String name;


    private List<String> listNewName;

    private MyBR myBR;
    private RelativeLayout deleRelativeLayout;
    private View tailView;
    private List<RecentSearchBean> list;
    private View headView;


    @Override
    int setLayout() {
        return R.layout.activity_search_details;
    }

    @Override
    void initView() {
        //recyclerView = bindView(R.id.search_details_rv);
        gridView = bindView(R.id.search_details_gv);
        searchIv = bindView(R.id.iv_lib_search_sea_aty);
        clearEditText = bindView(R.id.et_lib_search_aty);
        listView = bindView(R.id.search_details_ls);
        //searchRvAdapter = new SearchRvAdapter(this);
        searchGvAdapter = new SearchGvAdapter(this);
        searchDetailsListViewAdapter = new SearchDetailsListViewAdapter(this);
        data = new ArrayList<>();
        backIv = bindView(R.id.iv_search_details_back);
        backIv.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        searchIv.setOnClickListener(this);
    }


    @Override
    void initData() {
//
//        NetHelper.MyRequest(url, SearchRvBean.class, new NetListener<SearchRvBean>() {
//            @Override
//            public void successListener(SearchRvBean response) {
//                data = response.getKeywords();
//                searchRvAdapter.setData(data);
//                recyclerView.setAdapter(searchRvAdapter);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchDetailsActivity.this,2, LinearLayoutManager.VERTICAL,false);
//                recyclerView.setLayoutManager(gridLayoutManager);
//            }
//
//            @Override
//            public void errorListener(VolleyError error) {
//
//            }
//        });
        getSearchData();
        showLs();
        // 广播
        myBR = new MyBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.dong.foodsect.activity.MY_BR");
        registerReceiver(myBR, intentFilter);
    }

    // 解除广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBR);
    }

    private void showLs() {
        list = DBTool.getInstance().queryAll();
//         取反 让新的数据放在上面


        listNewName = new ArrayList<>();
        if (list != null) {
            for (int i = list.size(); i > 0; i--) {
                listNewName.add(list.get(i - 1).getName());

            }
        }
        searchDetailsListViewAdapter.setClear();
        searchDetailsListViewAdapter.setData(listNewName);
        listView.setAdapter(searchDetailsListViewAdapter);

        // listView 行点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchDetailsActivity.this, SearchDetailsGridViewActivity.class);
                intent.putExtra("name", listNewName.get(i));
                startActivity(intent);
            }
        });

        // listView 加头布局
        if (list.size() > 0) {
            headView = LayoutInflater.from(this).inflate(R.layout.item_search_ls_head, null);
            listView.addHeaderView(headView);
        }else {
            listView.removeHeaderView(headView);
        }
        // listView 加尾布局
        if (list.size() > 0) {
            tailView = LayoutInflater.from(this).inflate(R.layout.item_search_ls_tail, null);
            listView.addFooterView(tailView);
            // 清空数据
            deleRelativeLayout = (RelativeLayout) tailView.findViewById(R.id.item_search_ls_tail_rl);
            deleRelativeLayout.setOnClickListener(this);
        } else {
            listView.removeFooterView(tailView);
        }
    }

    // 内部类
    class MyBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.getStringExtra("key");
            if (str.equals("新数据")) {
                List<RecentSearchBean> list = DBTool.getInstance().queryAll();
//         取反 让新的数据放在上面
                listNewName = new ArrayList<>();
                if (list != null) {
                    for (int i = list.size(); i > 0; i--) {
                        listNewName.add(list.get(i - 1).getName());

                    }
                }
                searchDetailsListViewAdapter.setClear();
                searchDetailsListViewAdapter.setData(listNewName);
                listView.setAdapter(searchDetailsListViewAdapter);

            }
        }
    }

    private void getSearchData() {
        NetHelper.MyRequest(url, SearchRvBean.class, new NetListener<SearchRvBean>() {
            @Override
            public void successListener(SearchRvBean response) {
                searchRvBean = new SearchRvBean();
                searchRvBean = response;
                data = response.getKeywords();
                searchGvAdapter.setData(data);
                gridView.setAdapter(searchGvAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


    /**
     * 显示Toast消息
     *
     * @param msg
     */
    private void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_details_back:
                finish();
                break;
            case R.id.iv_lib_search_sea_aty:
                if (TextUtils.isEmpty(clearEditText.getText())) {
                    clearEditText.setShakeAnimation();
                    showToast("用户名不能为空");
                } else {
//                    try {
//                        String newKey = new String(key.getBytes("gbk"),"utf-8");
//                        Log.d("LibSearchAty", newKey);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    nameKey = clearEditText.getText().toString();
                    if (!DBTool.getInstance().isSave(nameKey)) {
                        RecentSearchBean recentSearchBean = new RecentSearchBean(null, nameKey);
                        DBTool.getInstance().insertRecentSearchBean(recentSearchBean);
                    } else {
                        RecentSearchBean newRecentSearchBean = new RecentSearchBean(null, nameKey);
                        DBTool.getInstance().deleteBy(nameKey);
                        DBTool.getInstance().insertRecentSearchBean(newRecentSearchBean);
                    }
                    Intent intent = new Intent(SearchDetailsActivity.this, SearchDetailsGridViewActivity.class);
                    intent.putExtra("name", nameKey);
                    Log.d("rrr", nameKey);
                    startActivity(intent);

                }
                break;
            case R.id.item_search_ls_tail_rl:

                searchDetailsListViewAdapter.setClear();
                listNewName.clear();
                DBTool.getInstance().deleteAll();
                searchDetailsListViewAdapter.setData(listNewName);
                // 移除首尾布局
                listView.removeFooterView(tailView);
                listView.removeHeaderView(headView);
                break;
        }
    }

    // GridView 点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(SearchDetailsActivity.this, SearchDetailsGridViewActivity.class);
        String name = searchRvBean.getKeywords().get(i);
        if (!DBTool.getInstance().isSave(name)) {
            RecentSearchBean recentSearchBean = new RecentSearchBean(null, name);
            DBTool.getInstance().insertRecentSearchBean(recentSearchBean);
        } else {
            RecentSearchBean newRecentSearchBean = new RecentSearchBean(null, name);
            DBTool.getInstance().deleteBy(name);
            DBTool.getInstance().insertRecentSearchBean(newRecentSearchBean);
        }
        Log.d("www", name);
        intent.putExtra("name", name);
        intent.putExtra("page", i);
        startActivity(intent);

    }
}
