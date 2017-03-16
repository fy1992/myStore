package cn.dahe.dao.impl;

import cn.dahe.dao.IVipLevelDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.VipLevel;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("vipLevelDao")
public class VipLevelDaoImpl extends BaseDaoImpl<VipLevel> implements IVipLevelDao{
    @Override
    public Pager<VipLevel> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from VipLevel vipLevel where 1=1");
        int storeId = params.getIntParam1();
        List<Object> list = new ArrayList<>();
        if(storeId != -1 && storeId != 0){
            hql.append(" and vipLevel.storeId = ?");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<VipLevel> findByStoreId(int storeId) {
        String hql = "from VipLevel where storeId = ?";
        return this.list(hql, storeId);
    }
}
