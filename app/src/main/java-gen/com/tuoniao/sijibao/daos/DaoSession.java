package com.tuoniao.sijibao.daos;

import android.database.sqlite.SQLiteDatabase;

import com.tuoniao.sijibao.beans.AppDataBean;
import com.tuoniao.sijibao.beans.UserDataBean;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig appDataBeanDaoConfig;
    private final DaoConfig userDataBeanDaoConfig;

    private final AppDataBeanDao appDataBeanDao;
    private final UserDataBeanDao userDataBeanDao;

    public DaoSession(SQLiteDatabase db,
                      IdentityScopeType type,
                      Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
                              daoConfigMap) {
        super(db);

        appDataBeanDaoConfig = daoConfigMap.get(AppDataBeanDao.class)
                .clone();
        appDataBeanDaoConfig.initIdentityScope(type);

        userDataBeanDaoConfig = daoConfigMap.get(UserDataBeanDao.class)
                .clone();
        userDataBeanDaoConfig.initIdentityScope(type);

        appDataBeanDao = new AppDataBeanDao(appDataBeanDaoConfig, this);
        userDataBeanDao = new UserDataBeanDao(userDataBeanDaoConfig, this);

        registerDao(AppDataBean.class, appDataBeanDao);
        registerDao(UserDataBean.class, userDataBeanDao);
    }

    public void clear() {
        appDataBeanDaoConfig.getIdentityScope()
                .clear();
        userDataBeanDaoConfig.getIdentityScope()
                .clear();
    }

    public AppDataBeanDao getAppDataBeanDao() {
        return appDataBeanDao;
    }

    public UserDataBeanDao getUserDataBeanDao() {
        return userDataBeanDao;
    }

}
