package cn.dahe.dao.impl;

import cn.dahe.dao.ISemifinishedItemDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SemifinishedItem;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 半成品记录
 * Created by 冯源 on 2017/4/8.
 */
@Repository("semifinishedItemDao")
public class SemifinishedItemDaoImpl extends BaseDaoImpl<SemifinishedItem> implements ISemifinishedItemDao{
    @Override
    public Pager<SemifinishedItem> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        String info = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from SemifinishedItem where storeId = ?");
        list.add(storeId);
//        hql.append(" and semifinishedNum > 0");
        if(startTime != null){
            hql.append(" and semifinishedTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            hql.append(" and semifinishedTime <= ?");
            list.add(endTime);
        }
        if(StringUtils.isNotBlank(info)){
            if(StringUtil.isNumber(info)){
                hql.append(" and (goodsNo = ? or targetGoodsNo = ?)");
                list.add(info);
                list.add(info);
            }else{
                hql.append(" and (goodsName like ? or targetGoodsName like ?)");
                list.add("%" + info + "%");
                list.add("%" + info + "%");
            }
        }
        return this.find(hql.toString(), list, start, pageSize);
    }
}
