package cn.dahe.dao.impl;

import cn.dahe.dao.ISaleInfoDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SaleInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售单据
 * Created by fy on 2017/3/30.
 */
@Repository("salesInfoDao")
public class SaleInfoDaoImpl extends BaseDaoImpl<SaleInfo> implements ISaleInfoDao {
    @Override
    public Pager<SaleInfo> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from SaleInfo si where 1=1");
        int storeId = params.getIntParam1();
        int cashierId = params.getIntParam2();
        int payType = params.getIntParam3();
        int type = params.getIntParam4();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        String serialNum = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(payType != -1){
            hql.append(" and si.payType = ?");
            list.add(payType);
        }
        if(type != -1){
            hql.append(" and si.type = ?");
            list.add(type);
        }
        if(cashierId != -1){
            hql.append(" and si.cashierId = ?");
            list.add(cashierId);
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and si.markTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and si.markTime <= ?");
            list.add(endTime);
        }
        if(StringUtils.isNotBlank(serialNum)){
            hql.append(" and si.serialNum = ?");
            list.add(serialNum);
        }
        if(storeId != 0){
            hql.append(" and si.storeId = ?");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
