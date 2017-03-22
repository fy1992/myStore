package cn.dahe.service.impl;

import cn.dahe.dao.IClientGoodsRawDao;
import cn.dahe.model.ClientGoodsRaw;
import cn.dahe.service.IClientGoodsRawService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 冯源 on 2017/3/23.
 */
@Service("clientGoodsRawService")
public class ClientGoodsRawServiceImpl implements IClientGoodsRawService{
    @Resource
    private IClientGoodsRawDao clientGoodsRawDao;
    @Override
    public boolean add(ClientGoodsRaw t) {
        return clientGoodsRawDao.addAndGetId4Integer(t) != 0;
    }

    @Override
    public void del(int id) {
        clientGoodsRawDao.delete(id);
    }

    @Override
    public void update(ClientGoodsRaw t) {
        ClientGoodsRaw clientGoodsRaw = clientGoodsRawDao.findByRawNo(t.getRawNo(), t.getStoreId());
        clientGoodsRaw.setRawNum(t.getRawNum());
        clientGoodsRawDao.update(clientGoodsRaw);
    }

    @Override
    public ClientGoodsRaw get(int id) {
        return clientGoodsRawDao.get(id);
    }

    @Override
    public ClientGoodsRaw load(int id) {
        return clientGoodsRawDao.load(id);
    }

    @Override
    public List<ClientGoodsRaw> goodsRawListByCategories(int categories, int storeId) {
        return clientGoodsRawDao.findByCategoriesId(categories, storeId);
    }

    @Override
    public ClientGoodsRaw findByRawNo(String rawNo, int storeId) {
        return clientGoodsRawDao.findByRawNo(rawNo, storeId);
    }
}
