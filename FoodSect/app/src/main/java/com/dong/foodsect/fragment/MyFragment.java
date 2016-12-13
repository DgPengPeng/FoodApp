package com.dong.foodsect.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.foodsect.R;
import com.dong.foodsect.activity.MyCollectDetailsActivity;
import com.dong.foodsect.activity.MyDetailsActivity;
import com.dong.foodsect.activity.MySetDetailsActivity;
import com.dong.foodsect.bean.EventBusBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by dllo on 16/11/22.
 * <p>
 * 这是 我的  Fragment
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private TextView myTv;
    private ImageView myIv, headImg;
    private LinearLayout linearLayout;
    private String img;
    private String name;
    private EventBusBean eventBusBean;
    private SharedPreferences sp;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        myTv = (TextView) view.findViewById(R.id.tv_my_head_login);
        myIv = (ImageView) view.findViewById(R.id.my_set);
        linearLayout = bindView(R.id.my_collect);
        headImg = bindView(R.id.civ_my_avatar);
        linearLayout.setOnClickListener(this);
        myTv.setOnClickListener(this);
        myIv.setOnClickListener(this);

    }

    @Override
    void initData() {
        SharedPreferences sp = mcontext.getSharedPreferences("A", Context.MODE_PRIVATE);
        if (sp.getString("img","a").equals("a")){
            headImg.setImageResource(R.mipmap.ic_analyse_default);
        }else {
            Picasso.with(mcontext).load(sp.getString("img",null)).into(headImg);
        }
        myTv.setText(sp.getString("name","点击登录"));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EventBusBean eventBusBean) {
        name = eventBusBean.getName();
        img = eventBusBean.getImg();
        Picasso.with(mcontext).load(img).into(headImg);
        myTv.setText(name);

        SharedPreferences.Editor editor = mcontext.getSharedPreferences("A", Context.MODE_PRIVATE).edit();
        editor.putString("img", img);
        editor.putString("name", name);
        editor.commit();


    }

    // 注册
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    // 解除
    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    public void back() {

        Platform mPf = ShareSDK.getPlatform(getActivity(), SinaWeibo.NAME);
        //如果要删除授权信息，重新授权
        ShareSDK.removeCookieOnAuthorize(true);
        mPf.removeAccount();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_head_login:
                if (myTv.getText().equals("点击登录")) {
                    Intent intent = new Intent(mcontext, MyDetailsActivity.class);
                    startActivity(intent);
                } else {
                    SharedPreferences.Editor editor = mcontext.getSharedPreferences("A", Context.MODE_PRIVATE).edit();
                    editor.putString("img", null);
                    editor.putString("name", null);
                    editor.commit();
                    back();

                    myTv.setText("点击登录");
                    headImg.setImageResource(R.mipmap.ic_analyse_default);

                }

                break;
            case R.id.my_set:
                Intent intent1 = new Intent(mcontext, MySetDetailsActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_collect:
                Intent intent2 = new Intent(mcontext, MyCollectDetailsActivity.class);
                startActivity(intent2);
                break;

        }
    }
}
