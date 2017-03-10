package cn.dahe.dao.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/24.
 */
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store> implements IStoreDao {
    @Override
    public Pager<Store> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Store store where 1=1");
        int status = params.getStatus();
        int storeId = params.getIntParam4();
        List<Object> list = new ArrayList<>();
        hql.append(" and store.status = ?");
        list.add(status);
        if(storeId != 0){
            hql.append(" and store.parent.id = ?");
            list.add(storeId);
        }else{
            hql.append(" and store.parent.id is null");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<Store> findAll(int storeId, int removeId) {
        StringBuffer hql = new StringBuffer("from Store store where 1=1");
        if(storeId != 0){
            hql.append(" and (store.parent.id = ? or store.id = ?) and store.id <> ?");
            return this.list(hql.toString(), new Object[]{storeId, storeId, removeId});
        }
        hql.append(" and store.id <> ? and store.status = 1");
        return this.list(hql.toString(), removeId);
    }

    @Override
    public Store findByStoreNo(String storeNo) {
        String hql = "from Store where storeNo = ?";
        return (Store)this.queryByHql(hql, storeNo);
    }
}
