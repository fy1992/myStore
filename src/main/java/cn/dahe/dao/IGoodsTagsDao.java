package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTags;

import java.util.List;

/**
 * 商品标签
 * Created by fy on 2017/1/13.
 */
public interface IGoodsTagsDao extends IBaseDao<GoodsTags>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<GoodsTags> findByParam(int start, int pageSize, Pager<Object> params);
    /**
     *根据名称查询
     * @param name
     * @param storeId
     * @return
     */
     GoodsTags findByName(String name, int storeId);

    /**
     *查询全部
     * @param storeId
     * @return
     */
     List<GoodsTags> findAll(int storeId);
}
