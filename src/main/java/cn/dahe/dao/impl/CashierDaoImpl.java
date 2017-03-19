package cn.dahe.dao.impl;

import cn.dahe.dao.ICashierDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
@Repository("cashierDao")
public class CashierDaoImpl extends BaseDaoImpl<Cashier> implements ICashierDao{
    @Override
    public Pager<Cashier> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Cashier cashier where 1=1");
        int status = params.getStatus();
        String cashierInfo = params.getStringParam1();
        int storeId = params.getIntParam1();
        List<Object> list = new ArrayList<>();
        hql.append(" and cashier.status = ?");
        list.add(status);
        if(StringUtils.isNotBlank(cashierInfo)){
            if(StringUtil.isMobile(cashierInfo)){
                hql.append(" and cashier.phone = ?");
                list.add(cashierInfo);
            }else if(StringUtil.isNumber(cashierInfo)){
                hql.append(" and cashier.cashierNo = ?");
                list.add(cashierInfo);
            }else{
                hql.append(" and cashier.name like ?");
                list.add("%" + cashierInfo + "%");
            }
        }
        if(storeId != 0){
            hql.append(" and cashier.storeId = ?");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public Cashier findByCashierNo(int storeId, String cashierNo) {
        String hql = "from Cashier cashier where cashier.cashierNo = ? and cashier.storeId = ?";
        return (Cashier)this.queryByHql(hql, new Object[]{cashierNo, storeId});
    }
}
