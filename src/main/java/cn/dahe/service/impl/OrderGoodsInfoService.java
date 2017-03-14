package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dao.IOrderGoodsInfoDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.service.IOrderGoodsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/1/29.
 */
@Service("orderGoodsInfoService")
public class OrderGoodsInfoService implements IOrderGoodsInfoService{
    private Logger logger = LoggerFactory.getLogger(OrderGoodsInfoService.class);

    @Resource
    private IOrderGoodsInfoDao orderGoodsInfoDao;
    @Resource
    private IGoodsTrafficDao goodsTrafficDao;

    @Override
    public void add(OrderGoodsInfo t) {
        orderGoodsInfoDao.add(t);
    }

    @Override
    public void del(int id) {
        orderGoodsInfoDao.delete(id);
    }

    @Override
    public void update(OrderGoodsInfo t) {
        orderGoodsInfoDao.update(t);
    }

    @Override
    public OrderGoodsInfo get(int id) {
        return orderGoodsInfoDao.get(id);
    }

    @Override
    public OrderGoodsInfo load(int id) {
        return orderGoodsInfoDao.load(id);
    }

    @Override
    public Pager<OrderGoodsInfo> orderGoodsInfoList(String aDataSet) {
        Pager<Object> params = new Pager<>();
        List<OrderGoodsInfo> orderGoodsInfoList =  orderGoodsInfoDao.findByParam(0, 0, params);
        Pager<OrderGoodsInfo> result = new Pager<>();
        int len = orderGoodsInfoList.size();
        result.setAaData(orderGoodsInfoList);
        result.setiTotalRecords(len);
        result.setiTotalDisplayRecords(len);
        return result;
    }

    @Override
    public void editOrderGoodsInfo(List<OrderGoodsInfo> orderGoodsInfoList, int GoodsTrafficId) {
        for(OrderGoodsInfo orderGoodsInfo : orderGoodsInfoList){
            OrderGoodsInfo ogi = get(orderGoodsInfo.getId());
            ogi.setDistributeNum(orderGoodsInfo.getDistributeNum());
            ogi.setPrice(orderGoodsInfo.getPrice());
            update(ogi);
        }
        GoodsTraffic goodsTraffic = goodsTrafficDao.get(GoodsTrafficId);
        goodsTraffic.setStatus(2);
        goodsTrafficDao.update(goodsTraffic);
    }

    @Override
    public List<OrderGoodsInfo> findOrderGoodsInfosByGoodsTrafficId(int goodsTrafficId) {
        return orderGoodsInfoDao.findByGoodsTrafficId(goodsTrafficId);
    }

    @Override
    public List<OrderGoodsInfo> findOrderGoodsInfosByTrafficManageId(int trafficManageId) {
        return orderGoodsInfoDao.findByTrafficManageId(trafficManageId);
    }
}
