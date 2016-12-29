package cn.dahe.service.impl;

import cn.dahe.dao.IBaseDao;
import cn.dahe.service.IBaseService;

import javax.annotation.Resource;

/**
 * Created by fy on 2016/12/29.
 */
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Resource
    private IBaseDao<T> baseDao;

    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    @Override
    public void del(int id) {
        baseDao.delete(id);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public T get(int id) {
        return baseDao.get(id);
    }

    @Override
    public T load(int id) {
        return baseDao.load(id);
    }
}
