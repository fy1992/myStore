package cn.dahe.service.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IStoreGoodsTrafficDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.service.IStoreService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/24.
 */
@Service("storeService")
public class StoreServiceImpl implements IStoreService{
    private static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    @Resource
    private IStoreDao storeDao;
    @Resource
    private IStoreGoodsTrafficDao storeGoodsTrafficDao;

    @Override
    public void add(Store t) {
        t.setCreateDate(new Date());
        t.setStoreNo(Long.toString(NumberUtils.getNo(5)));
        int storeId = storeDao.addAndGetId4Integer(t);
        Store store = get(storeId);
        StoreGoodsTraffic storeGoodsTraffic = new StoreGoodsTraffic();
        storeGoodsTraffic.setStoreId(storeId);
        storeGoodsTraffic.setStoreName(store.getName());
        storeGoodsTrafficDao.add(storeGoodsTraffic);
    }

    @Override
    public void del(int id) {
        storeDao.delete(id);
    }

    @Override
    public void update(Store t) {
        storeDao.update(t);
    }

    @Override
    public Store get(int id) {
        return storeDao.get(id);
    }

    @Override
    public Store load(int id) {
        return storeDao.load(id);
    }

    @Override
    public Pager<Store> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 0;
        String startTime = "", endTime = "";
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
                }else if (jsonObject.get("name").equals("startTime")) {
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("store.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            return storeDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Store> findAll(int storeId) {
        return storeDao.findAll(storeId);
    }

    @Override
    public void add(StoreGoodsTraffic storeGoodsTraffic) {
        storeGoodsTrafficDao.add(storeGoodsTraffic);
    }

    @Override
    public void update(StoreGoodsTraffic storeGoodsTraffic) {
        storeGoodsTrafficDao.update(storeGoodsTraffic);
    }

    @Override
    public StoreGoodsTraffic findByStoreId(int id) {
        return storeGoodsTrafficDao.findByStoreId(id);
    }

    @Override
    public List<StoreGoodsTraffic> findAllStoreGoodsTraffic() {
        return storeGoodsTrafficDao.findAll();
    }

    @Override
    public void updateStoreGoodsTraffics(String storeGoodsTraffics) {
        List<StoreGoodsTraffic> storeGoodsTrafficList = JSON.parseArray(storeGoodsTraffics, StoreGoodsTraffic.class);
        for(StoreGoodsTraffic storeGoodsTraffic : storeGoodsTrafficList){
            StoreGoodsTraffic sgt = storeGoodsTrafficDao.get(storeGoodsTraffic.getId());
            sgt.setDifferentOpt(storeGoodsTraffic.getDifferentOpt());
            sgt.setPayOnline(storeGoodsTraffic.getPayOnline());
            sgt.setPreparePriceType(storeGoodsTraffic.getPreparePriceType());
            sgt.setPrepareStoreId(storeGoodsTraffic.getPrepareStoreId());
            sgt.setPrepareStoreName(storeGoodsTraffic.getPrepareStoreName());
            storeGoodsTrafficDao.update(sgt);
        }
        Map<String, String> map = new HashMap<>();
    }
}
