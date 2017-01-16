package cn.dahe.service;

import cn.dahe.model.User;

import java.util.List;

/**
 * Created by fy on 2016/12/29.
 */
public interface IBaseService<T> {
    void add(T t);
    void del(int id);
    void update(T t);
    T get(int id);
    T load(int id);

    /**
     * 查询每个店铺下的重复
     * @param name
     * @param user
     * @return
     */
    T findByName(String name, User user);

    /**
     * 查询每个店铺下所有的
     * @return
     */
    List<T> findAll();
}
