package com.tuoniao.sijibao.helpers.base;

import java.util.List;

/**
 * @author zhangshuai
 * 数据库基础操作通用类
 */
public interface BaseDaoHelper {

    /**
     * 添加单个数据
     *
     * @param t   待存对象
     * @param <T> 数据对象
     */
    <T> void add(T t);

    /**
     * 精准单个删除
     *
     * @param id 索引值
     */
    void deleterById(String... id);

    /**
     * 清空
     */
    void clear();

    /**
     * 精准修改
     *
     * @param id 索引值
     */
    void updateById(String... id);

    /**
     * 精准搜索
     *
     * @param id 索引值
     */
    void searchById(String... id);

    /**
     * 获取所有数据
     */
    <T> List<T> getAll();

    /**
     * 获取表数据条数
     */
    int getCount();
}
