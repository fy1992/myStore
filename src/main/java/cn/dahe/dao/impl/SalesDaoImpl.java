package cn.dahe.dao.impl;

import cn.dahe.dao.ISalesDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Sales;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/25.
 */
@Repository("salesDao")
public class SalesDaoImpl extends BaseDaoImpl<Sales> implements ISalesDao{
    @Override
    public Pager<Sales> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Sales sales where 1=1");
        int status = params.getStatus();
        String sales = params.getStringParam1();
        int storeId = params.getIntParam1();
        List<Object> list = new ArrayList<>();
        hql.append(" and sales.status = ?");
        list.add(status);
        if(StringUtils.isNotBlank(sales)){
            if(StringUtil.isMobile(sales)){
                hql.append(" and sales.phone = ?");
                list.add(sales);
            }else if(StringUtil.isNumber(sales)){
                hql.append(" and sales.salesNo = ?");
                list.add(sales);
            }else{
                hql.append(" and sales.name like ?");
                list.add("%" + sales + "%");
            }
        }
        if(storeId != 0){
            hql.append(" and sales.storeId = ?");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
