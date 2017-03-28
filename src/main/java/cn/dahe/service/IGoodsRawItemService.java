package cn.dahe.service;

import cn.dahe.model.GoodsRawItem;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/26.
 */
public interface IGoodsRawItemService {
    void add(GoodsRawItem t);
    void del(int id);
    void update(GoodsRawItem t);
    GoodsRawItem get(int id);
    GoodsRawItem load(int id);

    List<GoodsRawItem> findByGoodsId(int goodsId);

    /**
     * 添加商品的配方明细
     * @param goodsId
     * @param rawItems
     * @param useRawPrice
     */
    void addRawItems(int goodsId, String rawItems, int useRawPrice);
}
