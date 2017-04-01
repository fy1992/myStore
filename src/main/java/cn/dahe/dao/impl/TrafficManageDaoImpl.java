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
        String trafficNo = params.getStringParam1();
        String storeIdStr = params.getStringParam2();
        int storeId = params.getIntParam1();
        int trafficType = params.getIntParam2();
        if(trafficType != -1){
            hql.append(" and tm.trafficType = ?");
            list.add(trafficType);
        }
        if(storeId != 0){
            hql.append(" and tm.storeId = ?");
            list.add(storeId);
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
        if(StringUtils.isNotBlank(storeIdStr)){
            String[] storeIdArr = storeIdStr.split(",");
            hql.append(" and tm.storeId in (");
            for(int i = 0, len = storeIdArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(storeIdArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        if(StringUtils.isNotBlank(trafficNo)){
            hql.append(" and tm.trafficNo like");
            list.add("%" + trafficNo + "%");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
