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


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    // 对外提供一个获取Context 对象的方法

    public static Context getmContext(){
        return mContext;
    }

}
