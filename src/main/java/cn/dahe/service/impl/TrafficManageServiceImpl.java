package cn.dahe.service.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IOrderGoodsInfoDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoods;
import cn.dahe.model.Goods;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.Stock;
import cn.dahe.model.Store;
import cn.dahe.model.TrafficManage;
import cn.dahe.service.ITrafficManageService;
import cn.dahe.util.DateUtil;
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
        int status = -1;
        String startTime = "", endTime = "", trafficNo = "";
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
                }else if (jsonObject.get("name").equals("trafficNo")) {
                    trafficNo = jsonObject.get("value").toString();
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

                        Goods goods = goodsDao.findByGoodsNo(goodsNo);
                        Stock stock = goods.getStock();
                        stock.setGoodNum(stock.getGoodNum() - changeNum);
                        goods.setStock(stock);
                        goodsDao.update(goods);
                    }
                });
            });
        }
        return trafficManage;
    }
}
