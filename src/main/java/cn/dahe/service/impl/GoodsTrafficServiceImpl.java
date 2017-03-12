package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dao.IOrderGoodsInfoDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IStoreGoodsTrafficDao;
import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.model.TrafficManage;
import cn.dahe.service.IGoodsTrafficService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.NumberUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fy on 2017/1/23.
 */
@Service("goodsTrafficService")
public class GoodsTrafficServiceImpl implements IGoodsTrafficService {
    private static Logger logger = LoggerFactory.getLogger(GoodsTrafficServiceImpl.class);
    @Resource
    private IGoodsTrafficDao goodsTrafficDao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IStoreDao storeDao;
    @Resource
    private IOrderGoodsInfoDao orderGoodsInfoDao;
    @Resource
    private ITrafficManageDao trafficManageDao;
    @Resource
    private IStoreGoodsTrafficDao storeGoodsTrafficDao;

    @Override
    public void add(GoodsTrafficDto t, int storeId) {
        JSONArray json = JSONArray.parseArray(t.getGoodsMapStr());
        GoodsTraffic goodsTraffic = new GoodsTraffic();
        goodsTraffic.setWishTime(DateUtil.format(t.getWishTime(), "yyyy-MM-dd"));
        goodsTraffic.setDescription(t.getDescription());
        goodsTraffic.setStatus(0);
        goodsTraffic.setOrderTime(new Date());
        Store store = storeDao.get(storeId);
        goodsTraffic.setOrderStoreId(storeId);
        goodsTraffic.setOrderStoreName(store.getName());
        int goodsTrafficId = goodsTrafficDao.addAndGetId4Integer(goodsTraffic);
        json.forEach(s -> {
            JSONObject map = JSONObject.parseObject(s.toString());
            Goods goods = goodsDao.get((Integer)map.get("goodsid"));
            OrderGoodsInfo goodsInfo = new OrderGoodsInfo(goods);
            //请求量
            goodsInfo.setOrderNum((Integer)map.get("goodsnum"));
            //小计
            goodsInfo.setPriceSum(goodsInfo.getOrderNum()*goodsInfo.getPrice());
            goodsInfo.setGoodsTrafficId(goodsTrafficId);
            goodsInfo.setDistributeNum(goodsInfo.getOrderNum());
            orderGoodsInfoDao.add(goodsInfo);
        });
    }

    @Override
    public void del(int id) {
        goodsTrafficDao.delete(id);
    }

    @Override
    public void update(GoodsTraffic t) {
        goodsTrafficDao.update(t);
    }

    @Override
    public GoodsTraffic get(int id) {
        return goodsTrafficDao.get(id);
    }

    @Override
    public GoodsTraffic load(int id) {
        return goodsTrafficDao.load(id);
    }

    @Override
    public Pager<GoodsTraffic> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = -1;
        String startTime = "", endTime = "";
        int timeType = 0; // 0 订货时间  1 发货时间
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("timeType")) {
                    timeType = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setStatus(status);
            params.setIntParam1(timeType);
            params.setOrderColumn("goodsTraffic.id");
            params.setOrderDir("desc");
            params.setIntParam2(storeId);
            return goodsTrafficDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAuditGoodsTraffic(int id, int type) {
        GoodsTraffic goodsTraffic = get(id);
        goodsTraffic.setStatus(type);
        goodsTrafficDao.update(goodsTraffic);
    }

    @Override
    public void prepareGoods(int id, String orderGoodsInfos) {
        GoodsTraffic goodsTraffic = get(id);
        //新建货流管理实例
        TrafficManage trafficManage = new TrafficManage();
        trafficManage.setStoreId(goodsTraffic.getOrderStoreId());//进货门店
        trafficManage.setDescription(goodsTraffic.getDescription());
        trafficManage.setOrderDate(new Date());
        trafficManage.setStatus(0); //待确认进货
        trafficManage.setTrafficType(1); // 进货单
        List<OrderGoodsInfo> ogis =  orderGoodsInfoDao.findByGoodsTrafficId(id);
        int priceSum = 0, goodsNum = 0;
        for(OrderGoodsInfo orderGoodsInfo : ogis){
            priceSum += orderGoodsInfo.getPriceSum();
            goodsNum += orderGoodsInfo.getDistributeNum();
        }
        trafficManage.setTotalPrice(priceSum); //总价
        trafficManage.setGoodsNum(goodsNum); //货流量
        trafficManage.setTrafficNo(NumberUtils.getNoByTime());
        StoreGoodsTraffic storeGoodsTraffic = storeGoodsTrafficDao.findByStoreId(trafficManage.getStoreId());
        trafficManage.setOutStoreId(storeGoodsTraffic.getPrepareStoreId());//出货门店
        trafficManage.setOutStoreName(storeGoodsTraffic.getPrepareStoreName());

        int trafficManageId = trafficManageDao.addAndGetId4Integer(trafficManage);

        List<OrderGoodsInfo> orderGoodsInfoList = orderGoodsInfoDao.findByGoodsTrafficId(goodsTraffic.getId());
        List<OrderGoodsInfo> orderGoodsInfoList1 = JSON.parseArray(orderGoodsInfos, OrderGoodsInfo.class);
        for(int i = 0, len = orderGoodsInfoList.size(); i < len; i++){
            OrderGoodsInfo orderGoodsInfo = orderGoodsInfoList.get(i);
            orderGoodsInfo.setDistributeNum(orderGoodsInfoList1.get(i).getDistributeNum());
            orderGoodsInfo.setPrice(orderGoodsInfoList1.get(i).getPrice());
            orderGoodsInfo.setPriceSum(orderGoodsInfoList1.get(i).getPriceSum());
            orderGoodsInfo.setTrafficManageId(trafficManageId);
            orderGoodsInfoDao.update(orderGoodsInfo);
        }
    }
}
