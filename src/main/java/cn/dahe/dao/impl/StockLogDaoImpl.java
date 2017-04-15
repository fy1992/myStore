package cn.dahe.dao.impl;

import cn.dahe.dao.IStockLogDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.StockLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/2.
 */
@Repository("stockLogDao")
public class StockLogDaoImpl extends BaseDaoImpl<StockLog> implements IStockLogDao{
    @Override
    public Pager<StockLog> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from StockLog sl where 1=1");
        List<Object> list = new ArrayList<>();
        int status = params.getStatus();
        int storeId = params.getIntParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        hql.append(" and sl.optType = ?");
        list.add(status);
        if(storeId != -1){
            hql.append(" and sl.storeId = ? ");
            list.add(storeId);
        }
        if(startTime != null){
            hql.append(" and sl.optDate >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            hql.append(" and sl.optDate <= ?");
            list.add(endTime);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
