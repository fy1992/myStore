package cn.dahe.service;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsService{
    void add(Goods t);
    void del(int id);
    void update(Goods t);
    Goods get(int id);
    Goods load(int id);
    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsDto> goodsList(String aDataSet, int storeId);
}
