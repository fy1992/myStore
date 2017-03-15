package cn.dahe.service.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.model.ClientGoods;
import cn.dahe.service.IClientGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/3/14.
 */
@Service("clientGoodsService")
public class ClientGoodsServiceImpl implements IClientGoodsService{
    private static Logger logger = LoggerFactory.getLogger(ClientGoodsServiceImpl.class);
    @Resource
    private IClientGoodsDao clientGoodsDao;

    @Override
    public boolean add(ClientGoods t) {
        int id = clientGoodsDao.addAndGetId4Integer(t);
        return id != 0;
    }

    @Override
    public void del(int id) {
        clientGoodsDao.delete(id);
    }

    @Override
    public void update(ClientGoods t) {
        ClientGoods clientGoods = clientGoodsDao.get(t.getId());
        clientGoods.setGoodsNum(t.getGoodsNum());
        clientGoodsDao.update(clientGoods);
    }

    @Override
    public ClientGoods get(int id) {
        return clientGoodsDao.get(id);
    }

    @Override
    public ClientGoods load(int id) {
        return clientGoodsDao.load(id);
    }

    @Override
    public List<ClientGoods> goodsListByCategories(int categories) {
        return clientGoodsDao.findByCategoriesId(categories);
    }
}
