package cn.dahe.dao.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.model.ClientGoods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/14.
 */
@Repository("clientGoodsDao")
public class ClientGoodsDaoImpl extends BaseDaoImpl<ClientGoods> implements IClientGoodsDao{
    @Override
    public List<ClientGoods> findAll(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public List<ClientGoods> findByCategoriesId(int categoriesId) {
        String hql = "from ClientGoods where categoriesId = ?";
        return this.list(hql, categoriesId);
    }

    @Override
    public ClientGoods findByGoodsNo(String goodsNo) {
        String hql = "from ClientGoods where goodsNo = ?";
        return (ClientGoods)this.queryByHql(hql, goodsNo);
    }

    @Override
    public List<ClientGoods> findByStoreId(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }
}
