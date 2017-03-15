package cn.dahe.dao;

import cn.dahe.model.ClientGoods;

import java.util.List;

/**
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
    List<ClientGoods> findByCategoriesId(int categoriesId);

    /**
     * 根据商品编码查询
     * @param goodsNo
     * @return
     */
    ClientGoods findByGoodsNo(String goodsNo);
}
