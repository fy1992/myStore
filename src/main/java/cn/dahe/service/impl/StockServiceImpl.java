package cn.dahe.service.impl;

import cn.dahe.dao.IStockDao;
import cn.dahe.dao.IStockLogDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Stock;
import cn.dahe.model.StockLog;
import cn.dahe.service.IStockService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/2.
 */
@Service("stockService")
public class StockServiceImpl implements IStockService{
    @Resource
    private IStockDao stockDao;
    @Resource
    private IStockLogDao stockLogDao;

    @Override
    public boolean add(Stock t) {
        stockDao.addAndGetId4Integer(t);
        return false;
    }

    @Override
    public void del(int id) {
        stockDao.delete(id);
    }

    @Override
    public void update(Stock t) {
        Stock stock = get(t.getId());
        stockDao.update(stock);
    }

    @Override
    public Stock get(int id) {
        return null;
    }

    @Override
    public Stock load(int id) {
        return null;
    }

    @Override
    public Pager<StockLog> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 0;
        String startTime = "", endTime = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }else if (jsonObject.get("name").equals("optType")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }else if (jsonObject.get("name").equals("startTime")) {
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("sl.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            return stockLogDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(StockLog sl) {
        return stockLogDao.addAndGetId4Integer(sl) != 0;
    }
}
