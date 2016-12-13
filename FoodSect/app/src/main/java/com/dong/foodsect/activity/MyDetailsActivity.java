package com.dong.foodsect.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dong.foodsect.R;
import com.dong.foodsect.bean.EventBusBean;
import com.dong.foodsect.fragment.MyFragment;
import com.mob.tools.utils.UIHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by dllo on 16/11/25.
 * <p/>
 * 这是我的_登录详情页
 */
public class MyDetailsActivity extends BaseActivity implements View.OnClickListener,PlatformActionListener, Handler.Callback {

    private LinearLayout linearLayout;
    private ImageView inIv;
    private EventBus eventBus;

    // 第三方 登录
    private static final int MSG_TOAST = 1;
    private static final int MSG_ACTION_CCALLBACK = 2;
    private static final int MSG_CANCEL_NOTIFY = 3;
    @Override
    int setLayout() {
        return R.layout.activity_my_details;
    }

    @Override
    void initView() {
        inIv = bindView(R.id.iv_login_weibo);
        linearLayout = (LinearLayout) findViewById(R.id.my_details_ll);
        eventBus = EventBus.getDefault();
        linearLayout.setOnClickListener(this);
        inIv.setOnClickListener(this);
    }

    @Override
    void initData() {
        SharedPreferences.Editor editor = getSharedPreferences("Launcher",MODE_PRIVATE).edit();
        editor.putBoolean("isFirst",true);
        editor.commit();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_details_ll:
//                Intent intent = new Intent(MyDetailsActivity.this, MainActivity.class);
//                intent.putExtra("id", 1);
//                startActivity(intent);
                finish();
                break;
            case R.id.iv_login_weibo:
                thirdSinaLogin();
                break;
        }


    }

    // 第三方登录
    public void back() {

        Platform mPf = ShareSDK.getPlatform(MyDetailsActivity.this, SinaWeibo.NAME);
        //如果要删除授权信息，重新授权
        ShareSDK.removeCookieOnAuthorize(true);
        mPf.removeAccount();

    }


    /**
     * 新浪微博授权、获取用户信息页面
     */
    private void thirdSinaLogin() {
        //初始化新浪平台
        Platform pf = ShareSDK.getPlatform(MyDetailsActivity.this, SinaWeibo.NAME);
        pf.SSOSetting(true);
        //设置监听
        pf.setPlatformActionListener(MyDetailsActivity.this);
        //获取登陆用户的信息，如果没有授权，会先授权，然后获取用户信息
        pf.authorize();
    }

    /**
     * 新浪微博授权成功回调页面
     */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        /** res是返回的数据，例如showUser(null),返回用户信息，对其解析就行
         *   http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
         *   1、不懂如何解析hashMap的，可以上网搜索一下
         *   2、可以参考官网例子中的GetInforPage这个类解析用户信息
         *   3、相关的key-value,可以看看对应的开放平台的api
         *     如新浪的：http://open.weibo.com/wiki/2/users/show
         *     腾讯微博：http://wiki.open.t.qq.com/index.php/API%E6%96%87%E6%A1%A3/%E5%B8%90%E6%88%B7%E6%8E%A5%E5%8F%A3/%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8D%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E7%9A%84%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99
         *
         */
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    /**
     * 取消授权
     */
    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    /**
     * 授权失败
     */
    @Override
    public void onError(Platform platform, int action, Throwable t) {
        t.printStackTrace();
        t.getMessage();
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }



    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_TOAST: {
                String text = String.valueOf(msg.obj);
                Toast.makeText(MyDetailsActivity.this, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_ACTION_CCALLBACK: {
                switch (msg.arg1) {
                    case 1: {
                        // 成功, successful notification
                        //授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
                        //ShareSDK只保存以下这几个通用值
                        Platform pf = ShareSDK.getPlatform(MyDetailsActivity.this, SinaWeibo.NAME);
                        Log.e("sharesdk use_id", pf.getDb().getUserId()); //获取用户id
                        Log.e("sharesdk use_name", pf.getDb().getUserName());//获取用户名称
                        Log.e("sharesdk use_icon", pf.getDb().getUserIcon());//获取用户头像
//                        mThirdLoginResult.setText("授权成功"+"\n"+"用户id:" + pf.getDb().getUserId() + "\n" + "获取用户名称" + pf.getDb().getUserName() + "\n" + "获取用户头像" + pf.getDb().getUserIcon());
//                        View view = LayoutInflater.from(this).inflate(R.layout.fragment_my,null);
//                        TextView tv_my = (TextView) view.findViewById(R.id.tv_my);
//                        ImageView img_my = (ImageView) view.findViewById(R.id.iv_my);
//                        Button fragment_my_btn = (Button) view.findViewById(R.id.fragment_my_btn);
//
//                        tv_my.setText(pf.getDb().getUserName());
//                        Picasso.with(this).load(pf.getDb().getUserIcon()).into(img_my);
//                        fragment_my_btn.setText("退出");
//
                        EventBusBean busBean = new EventBusBean();
                        busBean.setImg(pf.getDb().getUserIcon());
                        busBean.setName(pf.getDb().getUserName());
//                        busBean.setBtn("退出");
                        eventBus.post(busBean);

                        //mPf.author()这个方法每一次都会调用授权，出现授权界面
                        //如果要删除授权信息，重新授权
                        //mPf.getDb().removeAccount();
                        //调用后，用户就得重新授权，否则下一次就不用授权
                    }
                    break;
                    case 2: {
//                        mThirdLoginResult.setText("登录失败");
                    }
                    break;
                    case 3: {
                        // 取消, cancel notification
//                        mThirdLoginResult.setText("取消授权");
                    }
                    break;
                }
            }
            break;
            case MSG_CANCEL_NOTIFY: {
                NotificationManager nm = (NotificationManager) msg.obj;
                if (nm != null) {
                    nm.cancel(msg.arg1);
                }
            }
            break;
        }
        return false;
    }
}
