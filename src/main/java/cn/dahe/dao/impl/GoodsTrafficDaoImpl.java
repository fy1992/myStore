package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/1/23.
 */
@Repository("goodsTrafficDao")
public class GoodsTrafficDaoImpl extends BaseDaoImpl<GoodsTraffic> implements IGoodsTrafficDao{
    @Override
    public Pager<GoodsTraffic> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from GoodsTraffic goodsTraffic where 1=1");
        int timeType = params.getIntParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        int status = params.getStatus();
        String storeIdStr = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(status != -2){
            hql.append(" and goodsTraffic.status = ?");
            list.add(status);
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(timeType == 0 ? " and goodsTraffic.orderTime >= ?" : " and goodsTraffic.wishTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(timeType == 0 ? " and goodsTraffic.orderTime <= ?" : " and goodsTraffic.wishTime <= ?");
            list.add(endTime);
        }
        if(StringUtils.isNotBlank(storeIdStr)){
            String[] storeIdArr = storeIdStr.split(",");
            hql.append(" and goodsTraffic.orderStoreId in (");
            for(int i = 0, len = storeIdArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(storeIdArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }
}
