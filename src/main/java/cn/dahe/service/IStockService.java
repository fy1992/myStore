package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Stock;
import cn.dahe.model.StockLog;

/**
 * Created by fy on 2017/3/2.
 */
public interface IStockService {
    boolean add(Stock t);
    void del(int id);
    void update(Stock t);
    Stock get(int id);
    Stock load(int id);

    boolean add(StockLog sl);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     */
    Pager<StockLog> findByParams(String aDataSet, int storeId);
}
