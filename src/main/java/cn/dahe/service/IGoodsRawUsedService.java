package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRawUsed;

/**
 * Created by fy on 2017/3/28.
 */
public interface IGoodsRawUsedService {
    boolean add(GoodsRawUsed t);
    void del(int id);
    void update(GoodsRawUsed t);
    GoodsRawUsed get(int id);
    GoodsRawUsed load(int id);

    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsRawUsed> goodsRawUsedList(String aDataSet, int storeId);
}
