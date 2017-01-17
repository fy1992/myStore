package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsUnit;

import java.util.List;

/**
 * 商品单位
 * Created by fy on 2017/1/13.
 */
public interface IGoodsUnitDao extends IBaseDao<GoodsUnit>{

    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<GoodsUnit> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     *根据名称查询
     * @param name
     * @param storeId
     * @return
     */
     GoodsUnit findByName(String name, int storeId);

    /**
     *查询全部
     * @return
     */
     List<GoodsUnit> findAll();
}
