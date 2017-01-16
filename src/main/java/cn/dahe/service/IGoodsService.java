package cn.dahe.service;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.User;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsService extends IBaseService<Goods>{
    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsDto> goodsList(String aDataSet, int storeId);
}
