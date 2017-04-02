package cn.dahe.service.impl;

import cn.dahe.dao.*;
import cn.dahe.dao.impl.SaleInfoDaoImpl;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.*;
import cn.dahe.service.ISaleInfoService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.NumberUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 销售单据
 * Created by fy on 2017/3/30.
 */
@Service("saleInfoService")
public class SaleInfoServiceImpl implements ISaleInfoService {
    @Resource
    private ISaleInfoDao saleInfoDao;
    @Resource
    private ISaleInfoItemDao saleInfoItemDao;
    @Resource
    private IStoreDao storeDao;
    @Resource
    private ISalesDao salesDao;
    @Resource
    private IVipDao vipDao;
    @Resource
    private IClientGoodsDao clientGoodsDao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IGoodsRawItemDao goodsRawItemDao;
    @Resource
    private IGoodsRawDao goodsRawDao;
    @Override
    public boolean add(SaleInfo t) {
        t.setMarkTime(new Date());
        return saleInfoDao.addAndGetId4Integer(t) != 0;
    }

    @Override
    public boolean add(ClientDataDto t, Cashier cashier) {
        SaleInfo saleInfo = new SaleInfo();
        saleInfo.setCashierId(cashier.getId());
        saleInfo.setCashierName(cashier.getName());
        saleInfo.setPayType(0);
        saleInfo.setMarkTime(new Date());
        saleInfo.setStoreId(cashier.getStoreId());
        Store store = storeDao.get(cashier.getStoreId());
        if(store != null){
            saleInfo.setStoreName(store.getName());
        }
        saleInfo.setSalesId(t.getSalesId());
        Sales sales = salesDao.get(t.getSalesId());
        if(sales != null){
            saleInfo.setSalesName(sales.getSalesName());
        }
        saleInfo.setType(0);
        saleInfo.setVipId(t.getVipId());
        saleInfo.setSerialNum(NumberUtils.getNoByTime());
        Vip vip = vipDao.get(t.getVipId());
        if(vip != null){
            saleInfo.setVipName(vip.getVipName());
            saleInfo.setVipNo(vip.getVipNo());
        }
        int id = saleInfoDao.addAndGetId4Integer(saleInfo);
        JSONArray json = JSONArray.parseArray(t.getOrderInfo());
        double price = 0.0, totalGain = 0.0;
        int totalSum = 0;
        for(int i = 0, len = json.size(); i < len; i++){
            JSONObject map = JSONObject.parseObject(json.get(i).toString());
            SaleInfoItem saleInfoItem = new SaleInfoItem();
            ClientGoods clientGoods = clientGoodsDao.findByGoodsNo(map.get("goodsNo").toString(), cashier.getStoreId());
            Goods goods = goodsDao.findByGoodsNo(clientGoods.getGoodsNo(), cashier.getStoreId());
            saleInfoItem.setSaleInfoId(id);
            saleInfoItem.setGoodsUnitName(clientGoods.getGoodsUnit());
            saleInfoItem.setGoodsName(clientGoods.getGoodsName());
            saleInfoItem.setGoodsNo(clientGoods.getGoodsNo());
            saleInfoItem.setGoodsPrice(clientGoods.getPrice());
            saleInfoItem.setGoodsNum((Integer)map.get("goodsNum"));
            saleInfoItem.setRealPrice(goods.getPrice());
            //若商品设置了通过原材料自动更新进货价
            if(goods.getUseRawPrice() == 1){
                saleInfoItem.setGain(((goods.getPrice() * 100 - goods.getBid() * 100) * saleInfoItem.getGoodsNum())/100);
            }else{
                double rawPrice = 0.0;
                List<GoodsRawItem> goodsRawItems = goodsRawItemDao.findByGoodsId(goods.getId());
                for(GoodsRawItem goodsRawItem : goodsRawItems){
                    GoodsRaw goodsRaw = goodsRawDao.get(goodsRawItem.getRawId());
                    rawPrice += goodsRaw.getPrice() * 100 * goodsRawItem.getRawNum();
                }
                saleInfoItem.setGain(((goods.getPrice() * 100 - rawPrice) * saleInfoItem.getGoodsNum())/100);
            }
            saleInfoItemDao.add(saleInfoItem);
            price += goods.getPrice()*100;
            totalGain += saleInfoItem.getGain()*100;
            totalSum += saleInfoItem.getGoodsNum();
        }
        saleInfo = get(id);
        saleInfo.setGoodsPrice(price/100);
        saleInfo.setRealPrice(price/100);
        saleInfo.setGain(totalGain/100);
        saleInfo.setGoodsNum(totalSum);
        update(saleInfo);
        return id != 0;
    }

    @Override
    public void del(int id) {
        saleInfoDao.delete(id);
    }

    @Override
    public void update(SaleInfo t) {
        saleInfoDao.update(t);
    }

    @Override
    public SaleInfo get(int id) {
        return saleInfoDao.get(id);
    }

    @Override
    public SaleInfo load(int id) {
        return saleInfoDao.load(id);
    }

    @Override
    public Pager<SaleInfo> saleInfoList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int type = -1, payType = -1, cashierId = -1;
        String serialNum = "", startTime = "", endTime = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("serialNum")) {
                    serialNum = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("cashierId")) {
                    cashierId = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("payType")) {
                    payType = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("type")) {
                    type = Integer.parseInt(jsonObject.get("value").toString());
                } else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setIntParam1(storeId);
            params.setIntParam2(cashierId);
            params.setIntParam3(payType);
            params.setIntParam4(type);
            params.setStringParam1(serialNum);
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("si.id");
            params.setOrderDir("desc");
            return saleInfoDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SaleInfoItem> findBySaleId(int saleId) {
        return saleInfoItemDao.findBySaleInfoId(saleId);
    }

    @Override
    public List<SaleInfo> saleInfoList(String info, String startTime, String endTime, int storeId) {
        Pager<Object> params = new Pager<>();
        params.setStringParam1(info);
        if(StringUtils.isNotBlank(startTime) && !startTime.equals("0")){
            params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
        }
        if(StringUtils.isNotBlank(endTime) && !endTime.equals("0")){
            params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
        }
        params.setIntParam1(storeId);
        return saleInfoDao.saleInfoList(params);
    }
}
