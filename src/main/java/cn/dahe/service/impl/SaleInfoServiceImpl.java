package cn.dahe.service.impl;

import cn.dahe.dao.*;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.*;
import cn.dahe.service.ISaleInfoService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.DecimalUtil;
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
    @Resource
    private ISaleCountDao saleCountDao;
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
        double price = 0.0, totalGain = 0.0, totalRawPrice = 0.0;
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
            //若商品配置了原材料并且设置了通过原材料自动更新进货价
            if(goods.getHasRaws() == 1 && goods.getUseRawPrice() == 1){
                double rawPrice = 0.0;
                List<GoodsRawItem> goodsRawItems = goodsRawItemDao.findByGoodsId(goods.getId());
                for(GoodsRawItem goodsRawItem : goodsRawItems){
                    GoodsRaw goodsRaw = goodsRawDao.get(goodsRawItem.getRawId());
                    rawPrice += goodsRaw.getPrice() * 100 * goodsRawItem.getRawNum();
                }
                saleInfoItem.setGain(DecimalUtil.getDouble(((goods.getPrice() * 100 - rawPrice) * saleInfoItem.getGoodsNum()),2));
                totalRawPrice += rawPrice * saleInfoItem.getGoodsNum();
            }else{
                saleInfoItem.setGain(DecimalUtil.getDouble(((goods.getPrice() * 100 - goods.getBid() * 100) * saleInfoItem.getGoodsNum()),2));
                totalRawPrice += goods.getBid() * 100 * saleInfoItem.getGoodsNum();
            }
            saleInfoItemDao.add(saleInfoItem);
            price += goods.getPrice() * 100 * saleInfoItem.getGoodsNum();
            totalGain += saleInfoItem.getGain() * 100;
            totalSum += saleInfoItem.getGoodsNum();
        }
        saleInfo = saleInfoDao.get(id);
        saleInfo.setGoodsPrice(DecimalUtil.getDouble(price, 2));
        saleInfo.setRealPrice(DecimalUtil.getDouble(price, 2));
        saleInfo.setGain(DecimalUtil.getDouble(totalGain, 2));
        saleInfo.setGoodsNum(totalSum);
        update(saleInfo);

        List<SaleCount> saleCountList = saleCountDao.findByDay(new Date(), new Date(), cashier.getStoreId());
        if(saleCountList != null && saleCountList.size() > 0){
            SaleCount saleCount = saleCountList.get(0);
            saleCount.setGain(DecimalUtil.getDouble(saleCount.getGain() * 100 + saleInfo.getGain() * 100,2));
            saleCount.setTurnover(DecimalUtil.getDouble(saleCount.getTurnover() * 100 + saleInfo.getRealPrice() * 100,2));
            saleCount.setOrderNum(saleCount.getOrderNum() + 1);
            saleCount.setPaidByMoney(DecimalUtil.getDouble(saleCount.getPaidByMoney()  * 100 + saleInfo.getRealPrice() * 100,2));
            saleCount.setIncome(saleCount.getGain());
            saleCount.setPay(DecimalUtil.getDouble(saleCount.getPay() * 100 + totalRawPrice,2));
            saleCountDao.update(saleCount);
        }
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

    @Override
    public List<SaleCount> findByDay(String startTime, String endTime, int storeId) {
        Date start = DateUtil.format(startTime, "yyyy-MM-dd");
        Date end = DateUtil.format(endTime, "yyyy-MM-dd");
        return saleCountDao.findByDay(start, end, storeId);
    }

    @Override
    public void addSaleCount(SaleCount saleCount) {
        saleCountDao.add(saleCount);
    }

    @Override
    public void delSaleCount(int id) {
        saleCountDao.delete(id);
    }

    @Override
    public SaleCount getSaleCount(int storeId, Date countDate) {
        List<SaleCount> list = saleCountDao.findByDay(countDate, countDate, storeId);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
