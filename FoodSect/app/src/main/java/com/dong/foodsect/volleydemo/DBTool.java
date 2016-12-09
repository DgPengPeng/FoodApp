package com.dong.foodsect.volleydemo;

import android.util.Log;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */
public class DBTool {

    private static DBTool ourInstance = new DBTool();
    private static RecentSearchBeanDao recentSearchBeanDao;
    private static CollectBeanDao collectBeanDao;


    // 对外提供getInstance 方法 获取本类的单例对象

    public static DBTool getInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        // 初始化XXXDao  对象
        recentSearchBeanDao = MyApp.getDaoSession().getRecentSearchBeanDao();
        collectBeanDao = MyApp.getDaoSession().getCollectBeanDao();
        return ourInstance;
    }

    private DBTool() {
    }

    // 增加单一对象的方法
    public void insertRecentSearchBean(RecentSearchBean recentSearchBean) {
        recentSearchBeanDao.insert(recentSearchBean);
    }

    // 增加单一Url的方法
    public void insertUrl(CollectBean collectBean) {
        collectBeanDao.insert(collectBean);
    }

    // 增加集合的方法
    public void insertList(List<RecentSearchBean> list) {
        recentSearchBeanDao.insertInTx(list);
    }

    // 删除 单一对象大方法
    public void deleteRecentSearchBean(RecentSearchBean recentSearchBean) {
        recentSearchBeanDao.delete(recentSearchBean);
    }

    // 删除集合的方法
    public void deleteAll() {
        recentSearchBeanDao.deleteAll();
    }

    // 删除Url集合的方法
    public void deleteUrlAll() {
        collectBeanDao.deleteAll();
    }

    // 根据id 进行删除操作
    public void deleteId(Long id) {
        recentSearchBeanDao.deleteByKey(id);
    }

    // 根据某一字段 进行删除
    public void deleteBy(String name) {
        DeleteQuery<RecentSearchBean> deleteQuery = recentSearchBeanDao.queryBuilder()
                .where(RecentSearchBeanDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    // 根据某一Url 进行删除
    public void deleteUrlBy(String url) {
        DeleteQuery<CollectBean> deleteQuery = collectBeanDao.queryBuilder()
                .where(CollectBeanDao.Properties.Url.eq(url)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    // 查询所有方法
    public List<RecentSearchBean> queryAll() {
        //查询方法一
        List<RecentSearchBean> list = recentSearchBeanDao.loadAll();
        Log.e("test", list.size() + "");
        //查询方法二
        //List<RecentSearchBean> recentSearchBeanList = recentSearchBeanDao.queryBuilder().list();
        return list;
    }

    // 查询Url 所有方法
    public List<CollectBean> queryUrlAll() {
        List<CollectBean> urlList = collectBeanDao.loadAll();
        return urlList;
    }

    // 查重方法
    // 根据姓名来查询
    public boolean isSave(String name) {
        QueryBuilder<RecentSearchBean> queryBuilder = recentSearchBeanDao.queryBuilder()
                .where(RecentSearchBeanDao.Properties.Name.eq(name));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }

    // 查重方法
    // 根据Url来查询
    public boolean isUrlSave(String url) {
        QueryBuilder<CollectBean> queryBuilder = collectBeanDao.queryBuilder()
                .where(CollectBeanDao.Properties.Url.eq(url));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
