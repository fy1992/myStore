package cn.dahe.dao.impl;

import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/1/30.
 */
@Repository("trafficManagerDao")
public class TrafficManageDaoImpl extends BaseDaoImpl<TrafficManage> implements ITrafficManageDao {
    @Override
    public Pager<TrafficManage> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from TrafficManage tm where 1=1");
        List<Object> list = new ArrayList<>();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        int status = params.getStatus();
        int storeId = params.getIntParam1();
        String trafficNo = params.getStringParam1();
        if(status != -1){
            hql.append(" and tm.status = ?");
            list.add(status);
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and tm.orderDate >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and tm.orderDate <= ?");
            list.add(endTime);
        }
        if(storeId != -1){
            hql.append(" and tm.storeId = ?");
            list.add(storeId);
        }
        if(StringUtils.isNotBlank(trafficNo)){
            hql.append(" and tm.trafficNo like");
            list.add("%" + trafficNo + "%");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
