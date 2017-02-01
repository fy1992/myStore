package cn.dahe.dao.impl;

import cn.dahe.dao.IStoreGoodsTrafficDao;
import cn.dahe.model.StoreGoodsTraffic;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/2/2.
 */
@Repository("storeGoodsTrafficDao")
public class StoreGoodsTrafficDaoImpl extends BaseDaoImpl<StoreGoodsTraffic> implements IStoreGoodsTrafficDao{
    @Override
    public StoreGoodsTraffic findByStoreId(int storeId) {
        String hql = "from StoreGoodsTraffic where storeId = ?";
        return (StoreGoodsTraffic) this.queryByHql(hql, storeId);
    }
}
