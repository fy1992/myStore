package cn.dahe.dao.impl;

import cn.dahe.dao.ISaleCountDao;
import cn.dahe.model.SaleCount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售统计
 * Created by 冯源 on 2017/3/22.
 */
@Repository("saleCountDao")
public class SaleCountDaoImpl extends BaseDaoImpl<SaleCount> implements ISaleCountDao{
    @Override
    public List<SaleCount> findByDay(Date startTime, Date endTime, int storeId) {
        StringBuffer hql = new StringBuffer("from SaleCount saleCount where 1=1");
        List<Object> list = new ArrayList();
        if(storeId != 0){
            hql.append(" and saleCount.storeId = ?");
            list.add(storeId);
        }
        hql.append(" and saleCount.countDate >= ?");
        list.add(startTime);
        hql.append(" and saleCount.countDate <= ?");
        list.add(endTime);
        return this.list(hql.toString(), list);
    }
}
