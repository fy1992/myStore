package cn.dahe.dao.impl;


import cn.dahe.dao.IVipDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/15.
 */
@Repository("vipDao")
public class VipDaoImpl extends BaseDaoImpl<Vip> implements IVipDao{
    @Override
    public Pager<Vip> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Vip vip where 1=1");
        int status = params.getStatus();
        int storeId = params.getIntParam1();
        String vipInfo = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(storeId != -1 && storeId != 0){
            hql.append(" and vip.storeId = ?");
            list.add(storeId);
        }
        if (StringUtils.isNotBlank(vipInfo)){
            if(StringUtil.isNumber(vipInfo) && !StringUtil.isMobile(vipInfo)){
                hql.append(" and vipLevel.vipNo = ?");
                list.add(vipInfo);
            }else if(StringUtil.isMobile(vipInfo)){
                hql.append(" and vipLevel.phone = ?");
                list.add(vipInfo);
            }else{
                hql.append(" and vipLevel.name like ?");
                list.add("%" + vipInfo + "%");
            }
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

    @Override
    public List<Vip> findByStorId(int storeId) {
        String hql = "from Vip where 1=1";
        if(storeId != 0){
            hql += " and storeId = ?";
            return this.list(hql, storeId);
        }
        return this.list(hql);
    }

    @Override
    public Vip findByOpenId(String openId) {
        String hql = "from Vip where openId = ?";
        return (Vip)this.queryByHql(hql, openId);
    }
}
