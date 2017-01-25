package cn.dahe.dao.impl;

import cn.dahe.dao.IOptLogDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.OptLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("optLogDao")
public class OptLogDaoImpl extends BaseDaoImpl<OptLog> implements IOptLogDao {
    @Override
    public Pager<OptLog> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        String hql = "from OptLog optLog where 1=1";
        List<Object> list = new ArrayList<>();
        if(storeId != 0){
            hql += " and optLog.storeId = ?";
            list.add(storeId);
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql += " and optLog.operatDate >= ?";
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql += " and optLog.operatDate <= ?";
            list.add(endTime);
        }
        hql += " order by " + params.getOrderColumn() + " " + params.getOrderDir();
        return this.find(hql, list, start, pageSize);
    }
}
