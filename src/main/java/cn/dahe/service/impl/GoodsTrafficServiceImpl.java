package cn.dahe.service.impl;

import cn.dahe.dao.*;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.*;
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
import java.util.Date;
import java.util.List;

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
    @Resource
    private IGoodsRawDao goodsRawDao;

    @Override
    public void add(ClientDataDto t, int storeId) {
        GoodsTraffic goodsTraffic = new GoodsTraffic();
        goodsTraffic.setWishTime(DateUtil.format(t.getWishTime(), "yyyy-MM-dd"));
        goodsTraffic.setDescription(t.getDescription());
        goodsTraffic.setStatus(0);
        goodsTraffic.setOrderTime(new Date());
        Store store = storeDao.get(storeId);
        //不是连锁店
        if(store.getMultiple() == 0){
            goodsTraffic.setPrepareStoreId(storeId);
            goodsTraffic.setPrepareStoreName(store.getName());
        }else{
            //是连锁店，则按照设置好的分店货流设置赋值
            StoreGoodsTraffic storeGoodsTraffic = storeGoodsTrafficDao.findByStoreId(storeId);
            goodsTraffic.setPrepareStoreId(storeGoodsTraffic.getPrepareStoreId());
            goodsTraffic.setPrepareStoreName(storeGoodsTraffic.getPrepareStoreName());
        }
        goodsTraffic.setOrderStoreId(storeId);
        goodsTraffic.setOrderStoreName(store.getName());
        goodsTraffic.setOrderType(t.getType());
        int goodsTrafficId = goodsTrafficDao.addAndGetId4Integer(goodsTraffic);
        JSONArray json = JSONArray.parseArray(t.getOrderInfo());
        for(int i = 0, len = json.size(); i < len; i++){
            JSONObject map = JSONObject.parseObject(json.get(i).toString());
            OrderGoodsInfo goodsInfo;
            if(t.getType() == 0){
                Goods goods = goodsDao.findByGoodsNo(map.get("goodsNo").toString(), storeId);
                goodsInfo = new OrderGoodsInfo(goods);
            }else{
                GoodsRaw goodsRaw = goodsRawDao.findByRawNo(map.get("goodsNo").toString(), storeId);
                goodsInfo = new OrderGoodsInfo(goodsRaw);
            }
            //请求量
            goodsInfo.setOrderNum((Integer)map.get("goodsNum"));
            goodsInfo.setDistributeNum(goodsInfo.getOrderNum());
            //小计
            goodsInfo.setPriceSum(goodsInfo.getOrderNum() * goodsInfo.getPrice());
            goodsInfo.setGoodsTrafficId(goodsTrafficId);
            goodsInfo.setDistributeNum(goodsInfo.getOrderNum());
            orderGoodsInfoDao.add(goodsInfo);
        }
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
        int s_id = 0;
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
                }else if (jsonObject.get("name").equals("storeId")){
                    s_id = Integer.parseInt(jsonObject.get("value").toString());
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
            if(s_id != 0){
                storeId = s_id;
            }
            List<Store> stores = storeDao.findByPid(storeId);
            StringBuffer sb = new StringBuffer();
            sb.append(storeId + ",");
            if(stores != null && stores.size() > 0) {
                for (Store store : stores) {
                    sb.append(store.getId() + ",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            params.setStringParam1(sb.toString());
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
    public boolean updatePrepareGoods(int id, String orderGoodsInfos) {
        GoodsTraffic goodsTraffic = get(id);
        goodsTraffic.setStatus(2);
        goodsTrafficDao.update(goodsTraffic);

        int priceSum = 0, goodsNum = 0;

        List<OrderGoodsInfo> orderGoodsInfoList = orderGoodsInfoDao.findByGoodsTrafficId(id);
        List<OrderGoodsInfo> orderGoodsInfoList1 = JSON.parseArray(orderGoodsInfos, OrderGoodsInfo.class);
        for(int i = 0, len = orderGoodsInfoList.size(); i < len; i++){
            OrderGoodsInfo orderGoodsInfo = orderGoodsInfoList.get(i);
            orderGoodsInfo.setDistributeNum(orderGoodsInfoList1.get(i).getDistributeNum());
            orderGoodsInfo.setPrice(orderGoodsInfoList1.get(i).getPrice());
            orderGoodsInfo.setPriceSum(orderGoodsInfoList1.get(i).getPriceSum());
            orderGoodsInfoDao.update(orderGoodsInfo);
        }

        //boolean mark = true;
        for(OrderGoodsInfo orderGoodsInfo : orderGoodsInfoList){
            priceSum += orderGoodsInfo.getPriceSum();
            goodsNum += orderGoodsInfo.getDistributeNum();
        }

        //新建货流管理实例
        TrafficManage trafficManage = new TrafficManage();
        trafficManage.setStoreId(goodsTraffic.getOrderStoreId());//进货门店
        trafficManage.setStoreName(goodsTraffic.getOrderStoreName());
        trafficManage.setDescription(goodsTraffic.getDescription());
        trafficManage.setOrderDate(new Date());
        trafficManage.setWishDate(goodsTraffic.getWishTime());
        trafficManage.setStatus(0); //待确认进货
        trafficManage.setTrafficType(1); // 进货单
        trafficManage.setTotalPrice(priceSum); //总价
        trafficManage.setGoodsNum(goodsNum); //货流量
        trafficManage.setTrafficNo(NumberUtils.getNoByTime());
        trafficManage.setOrderType(goodsTraffic.getOrderType());//货流类型 0 商品 1 原材料
        //配置出货门店
        StoreGoodsTraffic storeGoodsTraffic = storeGoodsTrafficDao.findByStoreId(trafficManage.getStoreId());
        int outStoreId = storeGoodsTraffic.getPrepareStoreId();
        trafficManage.setOutStoreId(outStoreId);
        String outStoreName = storeGoodsTraffic.getPrepareStoreName();
        trafficManage.setOutStoreName(StringUtils.isBlank(outStoreName) ? "" : outStoreName);

        int trafficManageId = trafficManageDao.addAndGetId4Integer(trafficManage);
        orderGoodsInfoList.forEach(orderGoodsInfo -> {
            orderGoodsInfo.setTrafficManageId(trafficManageId);
            orderGoodsInfoDao.update(orderGoodsInfo);
        });
        return true;
    }
}
