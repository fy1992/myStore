package cn.dahe.service;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.GoodsDtoSimple;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;

import java.util.List;

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

    /**
     * 根据类别查询
     * @param categories
     * @param storeId
     * @return
     */
    List<GoodsDtoSimple> goodsListByCategories(int categories, int storeId);

    /**
     * 商品排序
     * @param ids
     */
    void goodsSort(String ids);

    /**
     * 商品复制
     * @param storeId
     * @param ids
     */
    void goodsCopy(int storeId, String ids);
}
