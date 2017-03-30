package cn.dahe.service.impl;

import cn.dahe.dao.ISaleInfoDao;
import cn.dahe.dao.ISaleInfoItemDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SaleInfo;
import cn.dahe.model.SaleInfoItem;
import cn.dahe.service.ISaleInfoService;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 销售单据
 * Created by fy on 2017/3/30.
 */
@Service("saleInfoService")
public class SaleInfoServiceImpl implements ISaleInfoService {
    @Resource
    private ISaleInfoDao saleInfoDao;
    @Resource
    private ISaleInfoItemDao saleInfoItemDao;
    @Override
    public boolean add(SaleInfo t) {
        t.setMarkTime(new Date());

        return saleInfoDao.addAndGetId4Integer(t) != 0;
    }

    @Override
    public void del(int id) {
        saleInfoDao.delete(id);
    }

    @Override
    public void update(SaleInfo t) {

    }

    @Override
    public SaleInfo get(int id) {
        return saleInfoDao.get(id);
    }

    @Override
    public SaleInfo load(int id) {
        return saleInfoDao.load(id);
    }

    @Override
    public Pager<SaleInfo> saleInfoList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int type = -1, payType = -1, cashierId = -1;
        String serialNum = "", startTime = "", endTime = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("serialNum")) {
                    serialNum = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("cashierId")) {
                    cashierId = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("payType")) {
                    payType = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("type")) {
                    type = Integer.parseInt(jsonObject.get("value").toString());
                } else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setIntParam1(storeId);
            params.setIntParam2(cashierId);
            params.setIntParam3(payType);
            params.setIntParam4(type);
            params.setStringParam1(serialNum);
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("si.id");
            params.setOrderDir("desc");
            return saleInfoDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SaleInfoItem> findBySaleId(int saleId) {
        return saleInfoItemDao.findBySaleInfoId(saleId);
    }
}
