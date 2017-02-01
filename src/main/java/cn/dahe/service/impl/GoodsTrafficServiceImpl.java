package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.Store;
import cn.dahe.service.IGoodsTrafficService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by fy on 2017/1/23.
 */
@Service("goodsTrafficService")
public class GoodsTrafficServiceImpl implements IGoodsTrafficService {
    private static Logger logger = LoggerFactory.getLogger(GoodsUnitServiceImpl.class);
    @Resource
    private IGoodsTrafficDao goodsTrafficDao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public void add(GoodsTrafficDto t) {
        Map<Integer, Integer> map =  t.getGoodsMap();
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        GoodsTraffic goodsTraffic = new GoodsTraffic();
        Set<OrderGoodsInfo> orderGoodsInfoSet = new HashSet<>();
        for(Map.Entry<Integer, Integer> e : set){
            int id = e.getKey();
            goodsDao.get(id);

        }
        goodsTraffic.setWishTime(DateUtil.format(t.getWishTime(), "yyyy-MM-dd HH:mm:ss"));
        goodsTraffic.setDescription(t.getDescription());
        goodsTraffic.setGoodsInfoSet(orderGoodsInfoSet);
        goodsTraffic.setStatus(0);
        goodsTraffic.setOrderTime(new Date());
        Store store = storeDao.get(t.getStoreId());
        goodsTraffic.setOrderStoreId(t.getStoreId());
        goodsTraffic.setOrderStoreName(store.getName());
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
            params.setIntParam1(timeType);
            params.setOrderColumn("goodTraffic.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            return goodsTrafficDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void auditGoodsTraffic(int id) {
        GoodsTraffic goodsTraffic = get(id);
        goodsTraffic.setStatus(1);
        update(goodsTraffic);
    }
}
