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

    /**
     * 管理员添加用
     * @param t
     * @param u
     * @param currentUser
     * @return
     */
    int add(Store t, User u, User currentUser);

    /**
     * 注册用
     * @param store
     * @param user
     * @return
     */
    int add(Store store, User user);
    void del(int id);
    void update(Store t, User user);
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
     * @param removeId
     * @return
     */
    List<Store> findAll(int storeId, int removeId);

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
