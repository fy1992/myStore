package cn.dahe.dao.impl;

import cn.dahe.dao.IBadGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/20.
 */
@Repository("badGoodsDao")
public class BadGoodsDaoImpl extends BaseDaoImpl<BadGoods> implements IBadGoodsDao{
    @Override
    public Pager<BadGoods> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from BadGoods bg where 1=1");
        List<Object> list = new ArrayList<>();
        int storeId = params.getIntParam1();
        Date startTime = params.getEndTime();
        Date endTime = params.getEndTime();
        if(storeId != -1){
            hql.append(" and bg.storeId = ? ");
            list.add(storeId);
        }
        if(startTime != null){
            hql.append(" and bg.badTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            hql.append(" and bg.badTime <= ?");
            list.add(endTime);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
