package cn.dahe.dao.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.model.ClientGoods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/3/14.
 */
@Repository("clientGoodsDao")
public class ClientGoodsDaoImpl extends BaseDaoImpl<ClientGoods> implements IClientGoodsDao{
    @Override
    public List<ClientGoods> findAll(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public List<ClientGoods> findByCategoriesId(int categoriesId, int storeId) {
        String hql = "from ClientGoods where categoriesId = ? and storeId = ?";
        return this.list(hql, new Object[]{categoriesId, storeId});
    }

    @Override
    public ClientGoods findByGoodsNo(String goodsNo, int storeId) {
        String hql = "from ClientGoods where goodsNo = ? and storeId = ?";
        return (ClientGoods)this.queryByHql(hql, new Object[]{goodsNo, storeId});
    }

    @Override
    public List<ClientGoods> findByStoreId(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }
}
