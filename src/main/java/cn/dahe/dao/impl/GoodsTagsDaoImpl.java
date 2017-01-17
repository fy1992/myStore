package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsTagsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.GoodsUnit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("goodsTagsDao")
public class GoodsTagsDaoImpl extends BaseDaoImpl<GoodsTags> implements IGoodsTagsDao{
    @Override
    public GoodsTags findByName(String name, int storeId) {
        String hql = "from GoodsTags where name = ? and storeId = ?";
        return (GoodsTags)queryByHql(hql, new Object[]{name, storeId});
    }

    @Override
    public List<GoodsTags> findAll() {
        String hql = "from GoodsTags";
        return list(hql);
    }

    @Override
    public Pager<GoodsTags> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        String hql = "from GoodsTags goodsTags where 1=1";
        List<Object> list = new ArrayList<>();
        if(storeId != 0){
            hql += " and goodsTags.storeId = ?";
            list.add(storeId);
        }
        return this.find(hql, list, start, pageSize);
    }
}
