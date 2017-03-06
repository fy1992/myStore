package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsUnitService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsUnitService")
public class GoodsUnitServiceImpl implements IGoodsUnitService{
    private static Logger logger = LoggerFactory.getLogger(GoodsUnitServiceImpl.class);
    @Resource
    private IGoodsUnitDao goodsUnitDao;
    @Resource
    private IGoodsDao goodsDao;
    @Override
    public void add(GoodsUnit t) {
        goodsUnitDao.add(t);
    }

    @Override
    public boolean del(int id) {
        Pager<Object> params = new Pager<>();
        params.setIntParam2(id);
        List<Goods> goodsList = goodsDao.findByParam(params);
        if(goodsList == null || goodsList.size() == 0){
            goodsUnitDao.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public void update(GoodsUnit t) {
        goodsUnitDao.update(t);
    }

    @Override
    public GoodsUnit get(int id) {
        return goodsUnitDao.get(id);
    }

    @Override
    public GoodsUnit load(int id) {
        return goodsUnitDao.load(id);
    }

    @Override
    public void add(String name, int storeid) {
        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setName(name);
        goodsUnit.setStoreId(storeid);
        goodsUnitDao.add(goodsUnit);
    }

    @Override
    public Pager<GoodsUnit> findByParams(String aDataSet, int storeId) {
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
            params.setOrderColumn("goodsUnit.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            return goodsUnitDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GoodsUnit findByName(String name, User user) {
        logger.info("-- findByName dao --");
        return goodsUnitDao.findByName(name, user.getStoreId());
    }

    @Override
    public List<GoodsUnit> findAll(int storeId) {
        return goodsUnitDao.findAll(storeId);
    }

    @Override
    public void update(int id, String name) {
        GoodsUnit goodsUnit = get(id);
        goodsUnit.setName(name);
        update(goodsUnit);
    }
}
