package cn.dahe.service.impl;

import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;
import cn.dahe.service.ITrafficManageService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/2/1.
 */
@Service("trafficManageService")
public class TrafficManageServiceImpl implements ITrafficManageService{
    private static Logger logger = LoggerFactory.getLogger(TrafficManageServiceImpl.class);
    @Resource
    private ITrafficManageDao trafficManageDao;

    @Override
    public void add(TrafficManage t) {
        trafficManageDao.add(t);
    }

    @Override
    public void del(int id) {
        trafficManageDao.delete(id);
    }

    @Override
    public void update(TrafficManage t) {
        trafficManageDao.update(t);
    }

    @Override
    public TrafficManage get(int id) {
        return trafficManageDao.get(id);
    }

    @Override
    public TrafficManage load(int id) {
        return trafficManageDao.load(id);
    }

    @Override
    public Pager<TrafficManage> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = -1;
        String startTime = "", endTime = "", trafficNo = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("trafficNo")) {
                    trafficNo = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setStatus(status);
            params.setOrderColumn("tm.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setStringParam1(trafficNo);
            return trafficManageDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void importTrafficManageExcel(int storeId) {

    }
}
