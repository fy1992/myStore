package cn.dahe.dao.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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
        Date startTime = new java.sql.Date(params.getStartTime().getTime());
        Date endTime = new java.sql.Date(params.getEndTime().getTime());
        List<Object> list = new ArrayList<>();
        hql.append(" and store.status = ?");
        list.add(status);
        if(startTime != null){
            hql.append(" and store.orderTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            hql.append(" and store.orderTime >= ?");
            list.add(endTime);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
