package cn.dahe.dao;

import cn.dahe.model.ClientOrderItem;

import java.util.List;

/**
 * 客户端订单明细
 * Created by fy on 2017/3/16.
 */
public interface IClientOrderItemDao extends IBaseDao<ClientOrderItem>{

    /**
     * 根据订单id查询
     * @param clientOrderId
     * @return
     */
    List<ClientOrderItem> findByClientOrderId(int clientOrderId);
}
