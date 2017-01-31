package cn.dahe.dao;

import cn.dahe.model.Stock;

/**
 * 库存
 * Created by fy on 2017/1/31.
 */
public interface IStockDao extends IBaseDao<Stock>{
    /**
     * 根据商品条码查询
     * @param goodsNo
     * @return
     */
    Stock findByGoodsNo(String goodsNo);
}
