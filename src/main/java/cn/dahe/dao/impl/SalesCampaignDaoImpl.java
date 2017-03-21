package cn.dahe.dao.impl;

import cn.dahe.dao.ISalesCampaignDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Sales;
import cn.dahe.model.SalesCampaign;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("salesCampaignDao")
public class SalesCampaignDaoImpl extends BaseDaoImpl<SalesCampaign> implements ISalesCampaignDao{
    @Override
    public Pager<SalesCampaign> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from SalesCampaign sc where 1=1");
        int overdue = params.getIntParam3();
        int storeId = params.getIntParam1();
        int type = params.getIntParam2();
        String name = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        hql.append(" and sc.storeId = ?");
        list.add(storeId);
        if(type != -1){
            hql.append(" and sc.type = ?");
            list.add(type);
        }
        if(overdue != -1){
            hql.append(" and sc.overdue = ?");
            list.add(overdue);
        }
        if(StringUtils.isNotBlank(name)){
            hql.append(" and sc.name like ?");
            list.add("%" + name + "%");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<SalesCampaign> findAll() {
        String hql = "from SalesCampaign where overdue = 1";
        return this.list(hql);
    }
}
