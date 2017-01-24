package cn.dahe.dao.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/24.
 */
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store> implements IStoreDao {
    @Override
    public Pager<Store> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
