package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;

/**
 * 门店订单
 * Created by fy on 2017/1/23.
 */
public interface IGoodsTrafficDao extends IBaseDao<GoodsTraffic>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<GoodsTraffic> findByParam(int start, int pageSize, Pager<Object> params);
}
