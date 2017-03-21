package cn.dahe.service.impl;

import cn.dahe.dao.ICategoriesDao;
import cn.dahe.dao.IGoodsRawDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoodsRaw;
import cn.dahe.model.GoodsRaw;
import cn.dahe.service.IGoodsRawService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 冯源 on 2017/3/21.
 */
@Service("goodsRawService")
public class GoodsRawServiceImpl implements IGoodsRawService{
    private static Logger logger = LoggerFactory.getLogger(GoodsRawServiceImpl.class);
    @Resource
    private IGoodsRawDao goodsRawDao;
    @Resource
    private ICategoriesDao categoriesDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public boolean add(GoodsRaw t) {
        GoodsRaw goods = goodsRawDao.findByRawNo(t.getRawNo());
        if(goods == null) {
            goodsRawDao.add(t);
            return true;
        }
        return false;
    }

    @Override
    public void del(int id) {
        goodsRawDao.delete(id);
    }

    @Override
    public void update(GoodsRaw t) {

    }

    @Override
    public GoodsRaw get(int id) {
        return goodsRawDao.get(id);
    }

    @Override
    public GoodsRaw load(int id) {
        return goodsRawDao.load(id);
    }


    @Override
    public Pager<GoodsRaw> goodsRawList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1, categories = -1, supplier = -1, stockPage = 0;
        String goodsRawInfo = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("goodsRawInfo")) {
                    goodsRawInfo = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("categoriesId")) {
                    categories = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("supplierId")) {
                    supplier = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("stockPage")) {
                    stockPage = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            params.setOrderColumn("goods.id");
            params.setOrderDir("desc");
            params.setIntParam1(categories);
            params.setIntParam2(supplier);
            params.setIntParam4(storeId);
            params.setState(stockPage);
            params.setStringParam1(goodsRawInfo);
            return goodsRawDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
