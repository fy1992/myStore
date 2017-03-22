package cn.dahe.dao;

import cn.dahe.model.ClientGoodsRaw;

import java.util.List;

/**
 * 客户端原材料
 * Created by 冯源 on 2017/3/23.
 */
public interface IClientGoodsRawDao extends IBaseDao<ClientGoodsRaw>{
    /**
     * 查询全部原材料
     * @param storeId
     * @return
     */
    List<ClientGoodsRaw> findAll(int storeId);

    /**
     * 根據分類id查詢
     * @param categoriesId
     * @return
     */
    List<ClientGoodsRaw> findByCategoriesId(int categoriesId, int storeId);

    /**
     * 根据原材料编码查询
     * @param rawNo
     * @return
     */
    ClientGoodsRaw findByRawNo(String rawNo, int storeId);

    /**
     * 根据门店查询
     * @param storeId
     * @return
     */
    List<ClientGoodsRaw> findByStoreId(int storeId);
}
