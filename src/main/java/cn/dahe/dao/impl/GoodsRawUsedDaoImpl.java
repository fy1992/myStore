package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsRawUsedDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRawUsed;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/28.
 */
@Repository("goodsRawUsedDao")
public class GoodsRawUsedDaoImpl extends BaseDaoImpl<GoodsRawUsed> implements IGoodsRawUsedDao{
    @Override
    public Pager<GoodsRawUsed> findByParam(int start, int pageSize, Pager<Object> params) {
        int categoriesId = params.getIntParam1();
        int storeId = params.getIntParam2();
        String goodsInfo = params.getStringParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        List<Object> objectList = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from GoodsRawUsed goodsRawUsed where 1=1");
        if (StringUtils.isNotEmpty(goodsInfo) && StringUtils.isNotBlank(goodsInfo)){
            if(StringUtil.isNumber(goodsInfo)){
                hql.append(" and goodsRawUsed.rawNo like ?");
            }else{
                hql.append(" and goodsRawUsed.rawName like ?");
            }
            objectList.add("%" + goodsInfo + "%");
        }
        if(categoriesId != -1){
            hql.append(" and goodsRawUsed.categoriesId = ?");
            objectList.add(categoriesId);
        }
        if(storeId != 0){
            hql.append(" and goodsRawUsed.storeId = ?");
            objectList.add(storeId);
        }
        if(startTime != null){
            hql.append(" and goodsRawUsed.usedTime >= ?");
            objectList.add(startTime);
        }
        if(endTime != null){
            hql.append(" and goodsRawUsed.usedTime <= ?");
            objectList.add(endTime);
        }
        hql.append(" order by "+ params.getOrderColumn() + " " +params.getOrderDir());
        return this.find(hql.toString(), objectList, start, pageSize);
    }

    @Override
    public GoodsRawUsed findByRawNoAndTime(String rawNo, Date time, int storeId) {
        StringBuffer sql = new StringBuffer("select * from goodsRawUsed");
        sql.append(" where raw_no = ?");
        sql.append(" and DATE_FORMAT(used_time, '%Y-%m-%d') = ?");
        sql.append(" and store_id = ?");
        return (GoodsRawUsed) this.listBySql(sql.toString(), new Object[]{rawNo, time, storeId}, GoodsRawUsed.class, true).get(0);
    }
}
