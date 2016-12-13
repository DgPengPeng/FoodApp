package com.dong.foodsect.volleydemo;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.dong.foodsect.volleydemo.CollectBean;
import com.dong.foodsect.volleydemo.RecentSearchBean;

import com.dong.foodsect.volleydemo.CollectBeanDao;
import com.dong.foodsect.volleydemo.RecentSearchBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig collectBeanDaoConfig;
    private final DaoConfig recentSearchBeanDaoConfig;

    private final CollectBeanDao collectBeanDao;
    private final RecentSearchBeanDao recentSearchBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        collectBeanDaoConfig = daoConfigMap.get(CollectBeanDao.class).clone();
        collectBeanDaoConfig.initIdentityScope(type);

        recentSearchBeanDaoConfig = daoConfigMap.get(RecentSearchBeanDao.class).clone();
        recentSearchBeanDaoConfig.initIdentityScope(type);

        collectBeanDao = new CollectBeanDao(collectBeanDaoConfig, this);
        recentSearchBeanDao = new RecentSearchBeanDao(recentSearchBeanDaoConfig, this);

        registerDao(CollectBean.class, collectBeanDao);
        registerDao(RecentSearchBean.class, recentSearchBeanDao);
    }
    
    public void clear() {
        collectBeanDaoConfig.clearIdentityScope();
        recentSearchBeanDaoConfig.clearIdentityScope();
    }

    public CollectBeanDao getCollectBeanDao() {
        return collectBeanDao;
    }

    public RecentSearchBeanDao getRecentSearchBeanDao() {
        return recentSearchBeanDao;
    }

}
