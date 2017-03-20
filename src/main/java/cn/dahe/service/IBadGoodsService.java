package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;

/**
 * Created by fy on 2017/3/20.
 */
public interface IBadGoodsService {
    void add(BadGoods t);
    boolean del(int id);
    void update(BadGoods t);
    BadGoods get(int id);
    BadGoods load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<BadGoods> findByParams(String aDataSet, int storeId);
}
