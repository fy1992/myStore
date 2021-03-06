package cn.dahe.dao.impl;


import cn.dahe.dao.IVipDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;
import cn.dahe.util.NumberUtils;
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
    public Vip findByStorIdAndVipNo(String vipNo, String storeIds) {
        List<Object> list = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from Vip where vipNo = ?");
        list.add(vipNo);
        if(StringUtils.isNotBlank(storeIds)){
            String[] storeIdArr = storeIds.split(",");
            hql.append(" and storeId in (");
            for(int i = 0, len = storeIdArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(storeIdArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        List vipList = this.list(hql.toString(), list);
        return vipList != null && vipList.size() > 0 ? (Vip)vipList.get(0) : null;
    }

    @Override
    public Vip findByOpenId(String openId) {
        String hql = "from Vip where openId = ?";
        return (Vip)this.queryByHql(hql, openId);
    }

    @Override
    public List<Vip> findByVipInfo(String params, String storeIds) {
        StringBuffer hql = new StringBuffer("from Vip vip where 1=1");
        List<Object> list = new ArrayList<>();
        if(StringUtil.isMobile(params)){
            hql.append(" and vip.phone = ?");
            list.add(params);
        }else if(!StringUtil.isMobile(params) && StringUtil.isNumber(params)){
            hql.append(" and vip.vipNo = ?");
            list.add(params);
        }else{
            hql.append(" and vip.vipName like ?");
            list.add("%" + params + "%");
        }
        String[] storeIdsArr = storeIds.split(",");
        hql.append(" and vip.storeId in (");
        for(int i = 0, len = storeIdsArr.length; i < len; i++){
            hql.append("?,");
            list.add(Integer.parseInt(storeIdsArr[i]));
        }
        hql.deleteCharAt(hql.length() - 1);
        hql.append(")");
        return this.list(hql.toString(), list);
    }
}
