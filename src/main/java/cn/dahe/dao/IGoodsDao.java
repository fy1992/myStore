package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;

import java.util.List;

/**
 * 商品
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

    /**
     * 根据指定参数查询
     * @param params
     * @return
     */
    List<Goods> findByParam(Pager<Object> params);

    /**
     * 通过商品条码查询
     * @param goodsNo
     * @return
     */
    Goods findByGoodsNo(String goodsNo);

    /**
     * 查询全部商品
     * @param storeId
     * @return
     */
    List<Goods> findAll(int storeId);
}
