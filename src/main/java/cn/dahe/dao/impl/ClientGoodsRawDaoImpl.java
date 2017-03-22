package cn.dahe.dao.impl;

import cn.dahe.dao.IClientGoodsRawDao;
import cn.dahe.model.ClientGoodsRaw;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 冯源 on 2017/3/23.
 */
@Repository("clientGoodsRawDao")
public class ClientGoodsRawDaoImpl extends BaseDaoImpl<ClientGoodsRaw> implements IClientGoodsRawDao{
    @Override
    public List<ClientGoodsRaw> findAll(int storeId) {
        String hql = "from ClientGoodsRaw where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public List<ClientGoodsRaw> findByCategoriesId(int categoriesId, int storeId) {
        String hql = "from ClientGoodsRaw where categoriesId = ? and storeId = ?";
        return this.list(hql, new Object[]{categoriesId, storeId});
    }

    @Override
    public ClientGoodsRaw findByRawNo(String rawNo, int storeId) {
        String hql = "from ClientGoodsRaw where rawNo = ? and storeId = ?";
        return (ClientGoodsRaw)this.queryByHql(hql, new Object[]{rawNo, storeId});
    }

    @Override
    public List<ClientGoodsRaw> findByStoreId(int storeId) {
        String hql = "from ClientGoodsRaw where storeId = ?";
        return this.list(hql, storeId);
    }
}
