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
     * @param useRawPrice  自动更新成品进货价
     * @param Semifinished 是否是半成品
     * @param autoFinished 是否直接转化为成品
     * @param targetGoodsName  目标成品名称
     * @param targetGoodsId    目标成品id
     * @param targetGoodsNum   目标成品数量
     */
    void addRawItems(int goodsId, String rawItems, int useRawPrice, int Semifinished, int autoFinished, int targetGoodsId, String targetGoodsName, int targetGoodsNum);
}
