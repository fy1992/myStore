package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.model.User;

import java.util.List;

/**
 * Created by fy on 2017/1/24.
 */
public interface IStoreService {
    void add(Store t);
    boolean add(Store t, User user);
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

    /**
     * 查询全部店面
     * @param storeId
     * @return
     */
    List<Store> findAll(int storeId);

    void add(StoreGoodsTraffic storeGoodsTraffic);
    void update(StoreGoodsTraffic storeGoodsTraffic);

    /**
     * 根据门店id查询货流信息
     * @param id
     * @return
     */
    StoreGoodsTraffic findByStoreId(int id);

    List<StoreGoodsTraffic> findAllStoreGoodsTraffic();

    /**
     * 门店货流设置
     * @param storeGoodsTraffic
     */
    void updateStoreGoodsTraffics(StoreGoodsTraffic storeGoodsTraffic);
}
