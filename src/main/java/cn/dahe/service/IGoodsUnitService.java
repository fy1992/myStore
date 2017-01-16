package cn.dahe.service;

import cn.dahe.model.GoodsUnit;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsUnitService extends IBaseService<GoodsUnit>{
    /**
     * 添加
     * @param name
     */
    void add(String name);
}
