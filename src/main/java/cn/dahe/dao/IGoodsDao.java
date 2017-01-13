package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsDao extends IBaseDao<Goods>{
    /**
     * 带条件查询商品
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Goods> findByParam(int start, int pageSize, Pager<Object> params);
}
