package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService{
    private static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private IGoodsDao goodsDao;

    @Override
    public Pager<GoodsDto> goodsList(String aDataSet, User user) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = -1, categories = -1, supplier = -1, tags = -1;
        String goodsInfo = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("goodsInfo")) {
                    goodsInfo = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("categories")) {
                    categories = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("supplier")) {
                    supplier = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("tags")) {
                    tags = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            int storeId = user.getStoreId();
            params.setStatus(status);
            params.setOrderColumn("goods.id");
            params.setOrderDir("desc");
            params.setIntParam1(categories);
            params.setIntParam2(supplier);
            params.setIntParam3(tags);
            params.setIntParam4(storeId);
            params.setStringParam1(goodsInfo);
            Pager<Goods> goods_pager = goodsDao.findByParam(start, pageSize, params);
            Pager<GoodsDto> goods_dto_pager = new Pager<>();
            goods_dto_pager.setiTotalDisplayRecords(goods_pager.getiTotalDisplayRecords());
            goods_dto_pager.setiTotalRecords(goods_pager.getiTotalRecords());
            List<Goods> goodsList = goods_pager.getAaData();
            List<GoodsDto> goodsDtoList = new ArrayList<>(goodsList.size());
            for(Goods goods : goodsList){
                goodsDtoList.add(new GoodsDto(goods));
            }
            goods_dto_pager.setAaData(goodsDtoList);
            return goods_dto_pager;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
