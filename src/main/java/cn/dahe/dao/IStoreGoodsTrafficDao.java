package cn.dahe.dao;

import cn.dahe.model.StoreGoodsTraffic;

/**
 * Created by fy on 2017/2/2.
 */
public interface IStoreGoodsTrafficDao extends IBaseDao<StoreGoodsTraffic>{
    /**
     * 根据店面id查询
     * @param storeId
     * @return
     */
    StoreGoodsTraffic findByStoreId(int storeId);
}
