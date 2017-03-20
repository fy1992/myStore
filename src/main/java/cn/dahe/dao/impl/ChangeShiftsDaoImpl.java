package cn.dahe.dao.impl;


import cn.dahe.dao.IChangeShiftsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ChangeShifts;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/20.
 */
@Repository("changeShiftsDao")
public class ChangeShiftsDaoImpl extends BaseDaoImpl<ChangeShifts> implements IChangeShiftsDao{
    @Override
    public Pager<ChangeShifts> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from ChangeShifts cs where 1=1");
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        String storeIdStr = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(StringUtils.isNotBlank(storeIdStr)){
            String[] storeIdArr = storeIdStr.split(",");
            hql.append(" and cs.storeId in (");
            for(int i = 0, len = storeIdArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(storeIdArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and cs.endTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and cs.endTime <= ?");
            list.add(endTime);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
