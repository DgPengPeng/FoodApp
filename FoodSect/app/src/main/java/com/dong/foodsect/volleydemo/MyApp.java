package com.dong.foodsect.volleydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/29.
 *
 * 切记如何使用
 *
 * 清单文件中加入自己的app
 */
public class MyApp extends Application {
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    // 对外提供一个获取Context 对象的方法

    public static Context getmContext(){
        return mContext;
    }

    // 对外提供获取DaoMaster 对象
    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getmContext(),"RecentSearch.db",null);
        // 初始化DaoMaster 对象
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;

    }
    // 对外提供获取DaoSession对象
    public static DaoSession getDaoSession() {
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            // 初始化DapSession对象
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
