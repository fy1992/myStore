package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsRawUsedDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRawUsed;
import cn.dahe.service.IGoodsRawUsedService;
import cn.dahe.util.DecimalUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/28.
 */
@Service("goodsRawUsedService")
public class GoodsRawUsedServiceImpl implements IGoodsRawUsedService{
    private static Logger logger = LoggerFactory.getLogger(GoodsRawUsedServiceImpl.class);
    @Resource
    private IGoodsRawUsedDao goodsRawUsedDao;

    @Override
    public boolean add(GoodsRawUsed t) {
        return goodsRawUsedDao.addAndGetId4Integer(t) != 0;
    }

    @Override
    public void del(int id) {
        goodsRawUsedDao.delete(id);
    }

    @Override
    public void update(GoodsRawUsed t) {
        GoodsRawUsed gru = goodsRawUsedDao.get(t.getId());
        gru.setTotalPrice(DecimalUtil.getDouble(gru.getTotalPrice()*100 + t.getTotalPrice()*100,2));
        gru.setUsedNum(gru.getUsedNum() + t.getUsedNum());
        goodsRawUsedDao.update(t);
    }

    @Override
    public GoodsRawUsed get(int id) {
        return goodsRawUsedDao.get(id);
    }

    @Override
    public GoodsRawUsed load(int id) {
        return goodsRawUsedDao.load(id);
    }

    @Override
    public Pager<GoodsRawUsed> goodsRawUsedList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int categories = -1;
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
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("goodsRawUsed.id");
            params.setOrderDir("desc");
            params.setIntParam1(categories);
            params.setIntParam2(storeId);
            params.setStringParam1(goodsRawInfo);
            return goodsRawUsedDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
