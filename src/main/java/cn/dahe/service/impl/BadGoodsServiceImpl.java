package cn.dahe.service.impl;

import cn.dahe.dao.IBadGoodsDao;
import cn.dahe.dao.IBadGoodsItemDao;
import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsRawDao;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;
import cn.dahe.model.BadGoodsItem;
import cn.dahe.model.Cashier;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsRaw;
import cn.dahe.model.Store;
import cn.dahe.service.IBadGoodsService;
import cn.dahe.service.IStoreService;
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
 * 商品报损
 * Created by fy on 2017/3/20.
 */
@Service("badGoodsService")
public class BadGoodsServiceImpl implements IBadGoodsService{
    private static Logger logger = LoggerFactory.getLogger(BadGoodsServiceImpl.class);
    @Resource
    private IBadGoodsDao badGoodsDao;
    @Resource
    private IStoreService storeService;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IGoodsRawDao goodsRawDao;
    @Resource
    private IBadGoodsItemDao badGoodsItemDao;

    @Override
    public int add(BadGoods t) {
        return badGoodsDao.addAndGetId4Integer(t);
    }

   @Override
    public void add(ClientDataDto clientDataDto, Cashier cashier) {
       String orderInfo = clientDataDto.getOrderInfo();
       int storeId = cashier.getStoreId();
       Store store = storeService.get(storeId);
       JSONArray json = JSONArray.parseArray(orderInfo);
       if(json.size() > 0){
           BadGoods t = new BadGoods();
           t.setStoreId(storeId);
           if(store != null){
               t.setStoreName(store.getName());
           }
           t.setBadTime(new Date());
           t.setCashierId(cashier.getId());
           t.setCashierName(cashier.getName());
           t.setDescription(clientDataDto.getDescription());
           int badGoodsId = add(t);
           double totalPrice = 0.0;
           for(int i = 0, len = json.size(); i < len; i++){
                JSONObject map = JSONObject.parseObject(json.get(i).toString());
                BadGoodsItem badGoodsItem = new BadGoodsItem();
                double price;
                if(clientDataDto.getType() == 0){
                    Goods goods = goodsDao.findByGoodsNo(map.get("goodsNo").toString(), storeId);
                    badGoodsItem.setGoodsName(goods.getName());
                    badGoodsItem.setGoodsNo(goods.getGoodsNo());
                    badGoodsItem.setGoodsUnitId(goods.getMainUnitId());
                    badGoodsItem.setGoodsUnitName(goods.getMainUnitName());
                    price = goods.getPrice();
                }else{
                    GoodsRaw goodsRaw = goodsRawDao.findByRawNo(map.get("goodsNo").toString(), storeId);
                    badGoodsItem.setGoodsName(goodsRaw.getName());
                    badGoodsItem.setGoodsNo(goodsRaw.getRawNo());
                    badGoodsItem.setGoodsUnitId(goodsRaw.getMainUnitId());
                    badGoodsItem.setGoodsUnitName(goodsRaw.getMainUnitName());
                    price = goodsRaw.getPrice();
                }
                badGoodsItem.setGoodsNum((Integer)map.get("goodsNum"));
                badGoodsItem.setPrice(price * (Integer)map.get("goodsNum"));
                badGoodsItem.setBadGoodsId(badGoodsId);
                badGoodsItemDao.add(badGoodsItem);
                totalPrice += badGoodsItem.getPrice()*100;
            }
            BadGoods badGoods = get(badGoodsId);
            badGoods.setPrice(totalPrice/100);
            update(badGoods);
        }
    }

    @Override
    public boolean del(int id) {
        return badGoodsDao.delete(id);
    }

    @Override
    public void update(BadGoods t) {
        badGoodsDao.update(t);
    }

    @Override
    public BadGoods get(int id) {
        return badGoodsDao.get(id);
    }

    @Override
    public BadGoods load(int id) {
        return badGoodsDao.load(id);
    }

    @Override
    public Pager<BadGoods> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        String vipInfo = "", startTime = "", endTime = "";;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("startTime")) {
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("vipInfo")) {
                    vipInfo = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("bg.id");
            params.setOrderDir("desc");
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setStringParam1(vipInfo);
            params.setIntParam1(storeId);
            return badGoodsDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BadGoodsItem> findByBadGoodsId(int badGoodsId) {
        return badGoodsItemDao.findByBadGoodsId(badGoodsId);
    }
}
