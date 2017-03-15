package cn.dahe.dao.impl;


import cn.dahe.dao.IVipDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 冯源 on 2017/3/15.
 */
@Repository("vipDao")
public class VipDaoImpl extends BaseDaoImpl<Vip> implements IVipDao{
    @Override
    public Pager<Vip> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Vip vip where 1=1");
        int status = params.getStatus();
        Date startTime = params.getEndTime();
        Date endTime = params.getEndTime();
        int storeId = params.getIntParam1();
        String userInfo = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and vip.registerDate >= ? ");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and vip.registerDate <= ? ");
            list.add(endTime);
        }
        if(storeId != -1 && storeId != 0){
            hql.append(" and vip.storeId = ?");
            list.add(storeId);
        }
        if(StringUtils.isNotBlank(userInfo)){
            hql.append(" and vip.username like ?");
            list.add("%" + userInfo + "%");
        }
        hql.append(" and vip.status = ?");
        list.add(status);
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public Vip findByVipNo(String vipNo) {
        String hql = "from Vip where vipNo = ?";
        return (Vip)this.queryByHql(hql, vipNo);
    }
}
