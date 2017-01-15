package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao{
    @Override
    public Pager<Goods> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
