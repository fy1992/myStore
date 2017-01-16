package cn.dahe.service.impl;

import cn.dahe.dao.IBaseDao;
import cn.dahe.model.User;
import cn.dahe.service.IBaseService;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public T findByName(String name, User user) {
        return baseDao.findByName(name, user.getStoreId());
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }
}
