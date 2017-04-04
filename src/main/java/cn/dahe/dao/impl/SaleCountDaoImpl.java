package cn.dahe.dao.impl;

import cn.dahe.dao.ISaleCountDao;
import cn.dahe.model.SaleCount;
import cn.dahe.util.DateUtil;
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
        StringBuffer sql = new StringBuffer("select * from t_sale_count where 1=1");
        List<Object> list = new ArrayList();
        if(storeId != 0){
            sql.append(" and store_id = ?");
            list.add(storeId);
        }
        sql.append(" and count_date >= ?");
        list.add(java.sql.Date.valueOf(DateUtil.format(startTime, "yyyy-MM-dd")));
        sql.append(" and count_date <= ?");
        list.add(java.sql.Date.valueOf(DateUtil.format(endTime, "yyyy-MM-dd")));
        return this.listBySqlAndList(sql.toString(), list, SaleCount.class, true);
    }
}
