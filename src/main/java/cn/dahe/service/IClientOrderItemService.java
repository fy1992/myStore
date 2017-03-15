package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrderItem;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/16.
 */
public interface IClientOrderItemService {
    void add(ClientOrderItem t);
    void del(int id);
    void update(ClientOrderItem t);
    ClientOrderItem get(int id);
    ClientOrderItem load(int id);

    /**
     * 订单商品信息列表
     * @param aDataSet
     * @return
     */
    Pager<ClientOrderItem> clientOrderItemList(String aDataSet);

    List<ClientOrderItem> findByClientOrderId(int clientOrderId);
}
