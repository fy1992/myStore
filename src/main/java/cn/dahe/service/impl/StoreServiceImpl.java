package cn.dahe.service.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.service.IStoreService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/24.
 */
@Service("storeService")
public class StoreServiceImpl implements IStoreService{
    private static Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);
    @Resource
    private IStoreDao storeDao;
    @Override
    public void add(Store t) {
        storeDao.add(t);
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
            return storeDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
