package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsUnit;
import cn.dahe.service.IGoodsUnitService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsUnitService")
public class GoodsUnitServiceImpl extends BaseServiceImpl<GoodsUnit> implements IGoodsUnitService{
    @Resource
    private IGoodsUnitDao goodsUnitDao;

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
            params.setIntParam4(storeId);
            return goodsUnitDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
