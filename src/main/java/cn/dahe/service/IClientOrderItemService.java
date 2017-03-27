package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrderItem;

import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
public interface IClientOrderItemService {
    void add(ClientOrderItem t);
    void del(int id);
    void update(ClientOrderItem t);
    ClientOrderItem get(int id);
    ClientOrderItem load(int id);

    List<ClientOrderItem> findByClientOrderId(int clientOrderId);

    List<ClientOrderItem> findByParams(Pager<Object> param);
}
