package cn.dahe.service.impl;

import cn.dahe.dao.ISemifinishedItemDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SemifinishedItem;
import cn.dahe.service.ISemifinishedItemService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 半成品记录
 * Created by 冯源 on 2017/4/8.
 */
@Service("semifinishedItemService")
public class SemifinishedItemServiceImpl implements ISemifinishedItemService{
    @Resource
    private ISemifinishedItemDao semifinishedItemDao;

    @Override
    public boolean add(SemifinishedItem t) {
        t.setSemifinishedTime(new Date());
        return semifinishedItemDao.add(t);
    }

    @Override
    public Pager<SemifinishedItem> semifinishedList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        String startTime = "", endTime = "", info = "";
        int categoriesId = -1;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("info")) {
                    info = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("cid")) {
                    categoriesId = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setIntParam2(categoriesId);
            params.setStringParam1(info);
            return semifinishedItemDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
