package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Store;

/**
 * Created by fy on 2017/1/24.
 */
public interface IStoreService {
    void add(Store t);
    void del(int id);
    void update(Store t);
    Store get(int id);
    Store load(int id);
    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Store> findByParams(String aDataSet, int storeId);
}
