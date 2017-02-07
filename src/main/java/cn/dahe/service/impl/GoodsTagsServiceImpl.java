package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsTagsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsTagsService;
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
@Service("goodsTagsService")
public class GoodsTagsServiceImpl implements IGoodsTagsService{
    private static Logger logger = LoggerFactory.getLogger(GoodsTagsServiceImpl.class);
    @Resource
    private IGoodsTagsDao goodsTagsDao;

    @Override
    public void add(GoodsTags t) {
        goodsTagsDao.add(t);
    }

    @Override
    public void del(int id) {
        goodsTagsDao.delete(id);
    }

    @Override
    public void update(GoodsTags t) {
        goodsTagsDao.update(t);
    }

    @Override
    public GoodsTags get(int id) {
        return goodsTagsDao.get(id);
    }

    @Override
    public GoodsTags load(int id) {
        return goodsTagsDao.load(id);
    }

    @Override
    public GoodsTags findByName(String name, User user) {
        return goodsTagsDao.findByName(name, user.getStoreId());
    }

    @Override
    public List<GoodsTags> findAll(int storeId) {
        return goodsTagsDao.findAll(storeId);
    }

    @Override
    public Pager<GoodsTags> findByParams(String aDataSet, int storeId) {
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
            params.setOrderColumn("goodsTags.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            return goodsTagsDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(String name, int storeId) {
        GoodsTags goodsTags = new GoodsTags();
        goodsTags.setName(name);
        goodsTags.setStoreId(storeId);
        goodsTagsDao.add(goodsTags);
    }

    @Override
    public void update(int id, String name) {
        GoodsTags goodsTags = get(id);
        goodsTags.setName(name);
        update(goodsTags);
    }
}
