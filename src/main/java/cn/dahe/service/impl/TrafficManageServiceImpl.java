package cn.dahe.service.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsRawDao;
import cn.dahe.dao.IOrderGoodsInfoDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IStoreGoodsTrafficDao;
import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoods;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsRaw;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.Stock;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.model.TrafficManage;
import cn.dahe.service.ITrafficManageService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.NumberUtils;
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
 * Created by fy on 2017/2/1.
 */
@Service("trafficManageService")
public class TrafficManageServiceImpl implements ITrafficManageService{
    private static Logger logger = LoggerFactory.getLogger(TrafficManageServiceImpl.class);
    @Resource
    private ITrafficManageDao trafficManageDao;
    @Resource
    private IStoreDao storeDao;
    @Resource
    private IClientGoodsDao clientGoodsDao;
    @Resource
    private IOrderGoodsInfoDao orderGoodsInfoDao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IGoodsRawDao goodsRawDao;
    @Resource
    private IStoreGoodsTrafficDao storeGoodsTrafficDao;

    @Override
    public void add(TrafficManage t) {
        trafficManageDao.add(t);
    }

    @Override
    public void del(int id) {
        trafficManageDao.delete(id);
    }

    @Override
    public void update(TrafficManage t) {
        trafficManageDao.update(t);
    }

    @Override
    public TrafficManage get(int id) {
        return trafficManageDao.get(id);
    }

    @Override
    public TrafficManage load(int id) {
        return trafficManageDao.load(id);
    }

    @Override
    public Pager<TrafficManage> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        String startTime = "", endTime = "", trafficNo = "";
        int s_id = 0, trafficType = -1;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                }else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("trafficNo")) {
                    trafficNo = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("storeId")){
                    s_id = Integer.parseInt(jsonObject.get("value").toString());
                }else if (jsonObject.get("name").equals("trafficType")){
                    trafficType = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("tm.id");
            params.setOrderDir("desc");
            params.setStringParam1(trafficNo);
            if(s_id != 0){
                storeId = s_id;
            }
            List<Store> stores = storeDao.findByPid(storeId);
            if(stores != null && stores.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (Store store : stores) {
                    sb.append(store.getId());
                }
                sb.deleteCharAt(sb.length() - 1);
                params.setStringParam2(sb.toString());
            }
            params.setIntParam1(storeId);
            params.setIntParam2(trafficType);
            return trafficManageDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void importTrafficManageExcel(int storeId) {

    }

    @Override
    public TrafficManage updatePrepare(int id, int type) {
        TrafficManage trafficManage = trafficManageDao.get(id);
        trafficManage.setStatus(type);
        trafficManage.setOptTime(new Date());
        trafficManageDao.update(trafficManage);
        //若配货通过
        if(type == 1){
            List<ClientGoods> clientGoodss = clientGoodsDao.findByStoreId(trafficManage.getStoreId());
            List<OrderGoodsInfo> orderGoodsInfoList = orderGoodsInfoDao.findByTrafficManageId(id);
            clientGoodss.forEach(clientGoods -> {
                String goodsNo = clientGoods.getGoodsNo();
                orderGoodsInfoList.forEach(orderGoodsInfo -> {
                    if(goodsNo.equals(orderGoodsInfo.getGoodsNo())){
                        int changeNum = orderGoodsInfo.getDistributeNum();
                        clientGoods.setGoodsNum(changeNum);
                        clientGoodsDao.update(clientGoods);

                        Goods goods = goodsDao.findByGoodsNo(goodsNo, clientGoods.getStoreId());
                        Stock stock = goods.getFinishedStock();
                        stock.setGoodNum(stock.getGoodNum() - changeNum);
                        goods.setFinishedStock(stock);
                        goodsDao.update(goods);
                    }
                });
            });
        }
        return trafficManage;
    }

    @Override
    public void addReturnedGoods(ClientDataDto clientDataDto, int storeId) {
        TrafficManage trafficManage = new TrafficManage();
        trafficManage.setTrafficType(0);//退货单
        trafficManage.setStatus(0);//待确认退货
        trafficManage.setTrafficNo(NumberUtils.getNoByTime());
        trafficManage.setStoreId(storeId);//退货门店
        Store store = storeDao.get(storeId);
        if(store != null){
            trafficManage.setStoreName(store.getName());
        }
        trafficManage.setDescription(clientDataDto.getDescription());
        trafficManage.setOrderDate(new Date());
        trafficManage.setWishDate(new Date());
        //配置出货门店
        StoreGoodsTraffic storeGoodsTraffic = storeGoodsTrafficDao.findByStoreId(trafficManage.getStoreId());
        int outStoreId = storeGoodsTraffic.getPrepareStoreId();
        trafficManage.setOutStoreId(outStoreId);
        String outStoreName = storeGoodsTraffic.getPrepareStoreName();
        trafficManage.setOutStoreName(StringUtils.isBlank(outStoreName) ? "" : outStoreName);
        int id = trafficManageDao.addAndGetId4Integer(trafficManage);


        JSONArray json = JSONArray.parseArray(clientDataDto.getOrderInfo());
        double price, totalPrice = 0.0;
        int totalSum = 0;
        for(int i = 0, len = json.size(); i < len; i++){
            JSONObject map = JSONObject.parseObject(json.get(i).toString());
            OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
            if(clientDataDto.getType() == 0){
                Goods goods = goodsDao.findByGoodsNo(map.get("goodsNo").toString(), storeId);
                orderGoodsInfo.setGoodsName(goods.getName());
                orderGoodsInfo.setGoodsNo(goods.getGoodsNo());
                orderGoodsInfo.setMainUnitId(goods.getMainUnitId());
                orderGoodsInfo.setMainUnitName(goods.getMainUnitName());
                price = goods.getPrice();
            }else{
                GoodsRaw goodsRaw = goodsRawDao.findByRawNo(map.get("goodsNo").toString(), storeId);
                orderGoodsInfo.setGoodsName(goodsRaw.getName());
                orderGoodsInfo.setGoodsNo(goodsRaw.getRawNo());
                orderGoodsInfo.setMainUnitId(goodsRaw.getMainUnitId());
                orderGoodsInfo.setMainUnitName(goodsRaw.getMainUnitName());
                price = goodsRaw.getPrice();
            }
            orderGoodsInfo.setTrafficManageId(id);
            orderGoodsInfo.setDistributeNum((Integer)map.get("goodsNum"));
            orderGoodsInfo.setPrice(price);
            orderGoodsInfo.setPriceSum(price * (Integer)map.get("goodsNum"));
            orderGoodsInfoDao.add(orderGoodsInfo);
            totalPrice += orderGoodsInfo.getPriceSum() * 100;
            totalSum += orderGoodsInfo.getDistributeNum();
        }

        trafficManage = trafficManageDao.get(id);
        trafficManage.setTotalPrice(totalPrice/100); // 总价
        trafficManage.setGoodsNum(totalSum); //总量
        trafficManageDao.update(trafficManage);
    }

    @Override
    public TrafficManage updateReturnedGoods(int id, int type) {
        TrafficManage trafficManage = trafficManageDao.get(id);
        trafficManage.setStatus(type);
        trafficManage.setOptTime(new Date());
        trafficManageDao.update(trafficManage);
        //若退货通过
        if(type == 1){
            List<ClientGoods> clientGoodss = clientGoodsDao.findByStoreId(trafficManage.getStoreId());
            List<OrderGoodsInfo> orderGoodsInfoList = orderGoodsInfoDao.findByTrafficManageId(id);
            clientGoodss.forEach(clientGoods -> {
                String goodsNo = clientGoods.getGoodsNo();
                orderGoodsInfoList.forEach(orderGoodsInfo -> {
                    if(goodsNo.equals(orderGoodsInfo.getGoodsNo())){
                        int changeNum = orderGoodsInfo.getDistributeNum();
                        clientGoods.setGoodsNum(clientGoods.getGoodsNum() - changeNum);
                        clientGoodsDao.update(clientGoods);

                        Goods goods = goodsDao.findByGoodsNo(goodsNo, clientGoods.getStoreId());
                        Stock stock = goods.getFinishedStock();
                        stock.setGoodNum(stock.getGoodNum() + changeNum);
                        goods.setFinishedStock(stock);
                        goodsDao.update(goods);
                    }
                });
            });
        }
        return trafficManage;
    }
}
