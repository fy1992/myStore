package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.OrderGoodsInfo;
import java.util.List;

/**
 * Created by fy on 2017/1/29.
 */
public interface IOrderGoodsInfoService {
    void add(OrderGoodsInfo t);
    void del(int id);
    void update(OrderGoodsInfo t);
    OrderGoodsInfo get(int id);
    OrderGoodsInfo load(int id);

    /**
     * 订货商品信息列表
     * @param aDataSet
     * @return
     */
    Pager<OrderGoodsInfo> orderGoodsInfoList(String aDataSet);

    /**
     * 配货
     * @param orderGoodsInfoList
     * @param goodsTrafficId
     */
    void editOrderGoodsInfo(List<OrderGoodsInfo> orderGoodsInfoList, int goodsTrafficId);

    /**
     * 通过订单id 查询订单商品
     * @param goodsTrafficId
     * @return
     */
    List<OrderGoodsInfo> findOrderGoodsInfosByGoodsTrafficId(int goodsTrafficId);

    List<OrderGoodsInfo> findOrderGoodsInfosByTrafficManageId(int trafficManageId);
}
