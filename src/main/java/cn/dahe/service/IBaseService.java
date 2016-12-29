package cn.dahe.service;

/**
 * Created by fy on 2016/12/29.
 */
public interface IBaseService<T> {
    void add(T t);
    void del(int id);
    void update(T t);
    T get(int id);
    T load(int id);
}
