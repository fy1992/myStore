package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRaw;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/21.
 */
public interface IGoodsRawService {
    boolean add(GoodsRaw t);
    void del(int id);
    void update(GoodsRaw t);
    GoodsRaw get(int id);
    GoodsRaw load(int id);
    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsRaw> goodsRawList(String aDataSet, int storeId);

    List<GoodsRaw> findByCategoriesId(int categoriesId, int storeId);
}
