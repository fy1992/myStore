package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsUnit;
import org.springframework.stereotype.Repository;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("goodsUnitDao")
public class GoodsUnitDaoImpl extends BaseDaoImpl<GoodsUnit> implements IGoodsUnitDao{
    @Override
    public Pager<GoodsUnit> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        String hql = "from GoodsUnit goodsUnit where 1=1";
        List<Object> list = new ArrayList<>();
        if(storeId != 0){
            hql += " and goodsUnit.storeId = ?";
            list.add(storeId);
        }
        return this.find(hql, list, start, pageSize);
    }
}
