package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Stock;

/**
 * Created by fy on 2017/3/2.
 */
public interface IStockService {
    boolean add(Stock t);
    void del(int id);
    void update(Stock t);
    Stock get(int id);
    Stock load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @return
     */
    Pager<Stock> findByParams(String aDataSet);


}
