package cn.dahe.service.impl;

import cn.dahe.dao.IBadGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;
import cn.dahe.service.IBadGoodsService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/20.
 */
@Service("badGoodsService")
public class BadGoodsServiceImpl implements IBadGoodsService{
    private static Logger logger = LoggerFactory.getLogger(BadGoodsServiceImpl.class);
    @Resource
    private IBadGoodsDao badGoodsDao;

    @Override
    public void add(BadGoods t) {
        badGoodsDao.addAndGetId4Integer(t);
    }

    @Override
    public boolean del(int id) {
        return badGoodsDao.delete(id);
    }

    @Override
    public void update(BadGoods t) {
        badGoodsDao.update(t);
    }

    @Override
    public BadGoods get(int id) {
        return badGoodsDao.get(id);
    }

    @Override
    public BadGoods load(int id) {
        return badGoodsDao.load(id);
    }

    @Override
    public Pager<BadGoods> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        String vipInfo = "", startTime = "", endTime = "";;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("startTime")) {
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("vipInfo")) {
                    vipInfo = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("bg.id");
            params.setOrderDir("desc");
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setStringParam1(vipInfo);
            params.setIntParam1(storeId);
            return badGoodsDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
