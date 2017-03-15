package cn.dahe.dao.impl;

import cn.dahe.dao.IClientOrderItemDao;
import cn.dahe.model.ClientOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("clientOrderItemDao")
public class ClientOrderItemDaoImpl extends BaseDaoImpl<ClientOrderItem> implements IClientOrderItemDao{

    @Override
    public List<ClientOrderItem> findByClientOrderId(int clientOrderId) {
        String hql = "from ClientOrderItem where clientOrderId = ?";
        return this.list(hql, clientOrderId);
    }
}
