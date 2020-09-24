package com.tuoniao.sijibao.helpers;

import com.tuoniao.sijibao.daos.AppDataBeanDao;
import com.tuoniao.sijibao.helpers.base.BaseDaoHelper;

import java.util.List;

public class AppConfigDbHelper implements BaseDaoHelper {

    private AppDataBeanDao mDao;

    public AppConfigDbHelper() {
        if (mDao == null) {

        }
    }

    public static AppConfigDbHelper getInstance() {
        return InnerClass.holder;
    }

    @Override
    public <T> void add(T t) {

    }

    @Override
    public void deleterById(String... id) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void updateById(String... id) {

    }

    @Override
    public void searchById(String... id) {

    }

    @Override
    public <T> List<T> getAll() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    private static class InnerClass {
        public static AppConfigDbHelper holder = new AppConfigDbHelper();
    }
}
