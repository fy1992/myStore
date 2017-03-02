package cn.dahe.dao.impl;

import cn.dahe.dao.IStockLogDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.StockLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        hql.append(" and sl.status = ?");
        list.add(status);
        if(storeId != -1){
            hql.append(" and sl.storeId = ? ");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
