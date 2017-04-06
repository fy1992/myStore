package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoods;

import java.util.List;

/**
 * 客户端展示商品
 * Created by fy on 2017/3/14.
 */
public interface IClientGoodsDao extends IBaseDao<ClientGoods>{
    /**
     * 查询全部商品
     * @param storeId
     * @return
     */
    List<ClientGoods> findAll(int storeId);

    /**
     * 根據分類id查詢
     * @param categoriesId
     * @return
     */
    List<ClientGoods> findByCategoriesId(int categoriesId, int storeId);

    /**
     * 根据商品编码查询
     * @param goodsNo
     * @return
     */
    ClientGoods findByGoodsNo(String goodsNo, int storeId);

    /**
     * 根据门店查询
     * @param storeId
     * @return
     */
    List<ClientGoods> findByStoreId(int storeId);

    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     */
    Pager<ClientGoods> findByParam(int start, int pageSize, Pager<Object> params);
}
