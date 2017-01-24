package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsTrafficDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.service.IGoodsTrafficService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/23.
 */
@Service("goodsTrafficService")
public class GoodsTrafficServiceImpl implements IGoodsTrafficService {
    private static Logger logger = LoggerFactory.getLogger(GoodsUnitServiceImpl.class);
    @Resource
    private IGoodsTrafficDao goodsTrafficDao;

    @Override
    public void add(GoodsTraffic t) {
        goodsTrafficDao.add(t);
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
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("store.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            return goodsTrafficDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
