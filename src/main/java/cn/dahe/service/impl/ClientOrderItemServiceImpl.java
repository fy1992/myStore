package cn.dahe.service.impl;

import cn.dahe.dao.IClientOrderItemDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrderItem;
import cn.dahe.service.IClientOrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Service("clientOrderService")
public class ClientOrderItemServiceImpl implements IClientOrderItemService{
    private Logger logger = LoggerFactory.getLogger(ClientOrderItemServiceImpl.class);
    @Resource
    private IClientOrderItemDao clientOrderItemDao;
    @Override
    public void add(ClientOrderItem t) {

    }

    @Override
    public void del(int id) {
        clientOrderItemDao.delete(id);
    }

    @Override
    public void update(ClientOrderItem t) {

    }

    @Override
    public ClientOrderItem get(int id) {
        return clientOrderItemDao.get(id);
    }

    @Override
    public ClientOrderItem load(int id) {
        return clientOrderItemDao.load(id);
    }

    @Override
    public Pager<ClientOrderItem> clientOrderItemList(String aDataSet) {
        return null;
    }

    @Override
    public List<ClientOrderItem> findByClientOrderId(int clientOrderId) {
        return clientOrderItemDao.findByClientOrderId(clientOrderId);
    }
}
