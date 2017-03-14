package cn.dahe.dao.impl;

import cn.dahe.dao.IOrderGoodsInfoDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.OrderGoodsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/1/29.
 */
@Repository("orderGoodsInfoDao")
public class OrderGoodsInfoDaoImpl extends BaseDaoImpl<OrderGoodsInfo> implements IOrderGoodsInfoDao{
    @Override
    public List<OrderGoodsInfo> findByParam(int start, int pageSize, Pager<Object> params) {
        int id = params.getIntParam1();
        String hql = "from OrderGoodsInfo orderGoodsInfo where orderGoodsInfo.goodsTraffic.id = ?";
        return this.list(hql, id);
    }

    @Override
    public List<OrderGoodsInfo> findByGoodsTrafficId(int id) {
        String hql = "from OrderGoodsInfo where goodsTrafficId = ?";
        return this.list(hql, id);
    }

    @Override
    public List<OrderGoodsInfo> findByTrafficManageId(int id) {
        String hql = "from OrderGoodsInfo where trafficManageId = ?";
        return this.list(hql, id);
    }
}
