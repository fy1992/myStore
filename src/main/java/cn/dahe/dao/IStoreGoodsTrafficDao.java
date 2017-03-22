package cn.dahe.dao;

import cn.dahe.model.StoreGoodsTraffic;

import java.util.List;

/**
 * 门店货流管理设置
 * Created by fy on 2017/2/2.
 */
public interface IStoreGoodsTrafficDao extends IBaseDao<StoreGoodsTraffic>{
    /**
     * 根据店面id查询
     * @param storeId
     * @return
     */
    StoreGoodsTraffic findByStoreId(int storeId);

    List<StoreGoodsTraffic> findAll();
}
