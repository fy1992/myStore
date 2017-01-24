package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/23.
 */
@Repository("goodsTrafficDao")
public class GoodsTrafficDaoImpl extends BaseDaoImpl<GoodsTraffic> implements IGoodsTrafficDao{
    @Override
    public Pager<GoodsTraffic> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
