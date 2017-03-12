package cn.dahe.service;

import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;

/**
 * Created by fy on 2017/1/23.
 */
public interface IGoodsTrafficService {
    void add(GoodsTrafficDto t, int storeId);
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
     * @param type -1 已作废 0 待审核 1 配货中 2 已完成
     */
    void updateAuditGoodsTraffic(int id, int type);

    /**
     * 门店配货
     * @param id
     * @param orderGoodsInfos
     */
    void prepareGoods(int id, String orderGoodsInfos);
}
