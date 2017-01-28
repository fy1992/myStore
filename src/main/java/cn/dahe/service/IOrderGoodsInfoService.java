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
     * @param GoodsTrafficId
     */
    void editOrderGoodsInfo(List<OrderGoodsInfo> orderGoodsInfoList, int GoodsTrafficId);
}
