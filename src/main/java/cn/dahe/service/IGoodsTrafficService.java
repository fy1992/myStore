package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;

/**
 * Created by fy on 2017/1/23.
 */
public interface IGoodsTrafficService {
    void add(GoodsTraffic t);
    void del(int id);
    void update(GoodsTraffic t);
    GoodsTraffic get(int id);
    GoodsTraffic load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsTraffic> findByParams(String aDataSet, int storeId);

    /**
     * 审核
     * @param id
     */
    void auditGoodsTraffic(int id);
}
