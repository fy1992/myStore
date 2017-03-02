package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.StockLog;

/**
 * Created by fy on 2017/3/2.
 */
public interface IStockLogDao extends IBaseDao<StockLog>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<StockLog> findByParam(int start, int pageSize, Pager<Object> params);
}
