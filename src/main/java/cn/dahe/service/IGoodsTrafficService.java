package cn.dahe.service;

import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;

/**
 * Created by fy on 2017/1/23.
 */
public interface IGoodsTrafficService {
    void add(GoodsTrafficDto t);
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
     * @param type 0 作废 1 审核通过
     */
    void auditGoodsTraffic(int id, int type);

    /**
     * 门店配货
     * @param id
     * @param orderGoodsInfos
     */
    void prepareGoods(int id, String orderGoodsInfos);
}
