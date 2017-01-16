package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsUnit;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsUnitService extends IBaseService<GoodsUnit>{
    /**
     * 添加
     * @param name
     * @param storeId
     */
    void add(String name, int storeId);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsUnit> findByParams(String aDataSet, int storeId);
}
