package com.dong.a_greendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/12/7.
 */
public class MyApp extends Application {

    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static  Context getContext(){
        return context;
    }

    // 对外提供获取DaoMaster对象
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"Person.db",null);
        // 初始化DaoMaster对象
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
