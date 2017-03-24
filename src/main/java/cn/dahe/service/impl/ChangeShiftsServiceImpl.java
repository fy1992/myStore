package cn.dahe.service.impl;

import cn.dahe.dao.IChangeShiftsDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.ChangeShifts;
import cn.dahe.model.Store;
import cn.dahe.service.IChangeShiftsService;
import cn.dahe.util.CacheUtils;
import cn.dahe.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/20.
 */
@Service("changeShiftsService")
public class ChangeShiftsServiceImpl implements IChangeShiftsService {
    private static Logger logger = LoggerFactory.getLogger(ChangeShiftsServiceImpl.class);
    @Resource
    private IChangeShiftsDao changeShiftsDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public int add(ChangeShifts t) {
        return changeShiftsDao.addAndGetId4Integer(t);
    }

    @Override
    public void del(int id) {
        changeShiftsDao.delete(id);
    }

    @Override
    public void update(ChangeShifts t, int num, double price) {
        t.setNum(t.getNum() + num);
        t.setTotalPrice(t.getTotalPrice() + price);
        changeShiftsDao.update(t);
    }

    @Override
    public void logout(Cashier cashier) {
        int id = (int) CacheUtils.getChangeShifts("cashier_"+cashier.getId());
        ChangeShifts changeShifts = changeShiftsDao.get(id);
        changeShifts.setEndTime(new Date());
        changeShiftsDao.update(changeShifts);
        CacheUtils.removeChangeShifts("cashier_"+cashier.getId());
    }

    @Override
    public ChangeShifts get(int id) {
        return changeShiftsDao.get(id);
    }

    @Override
    public ChangeShifts load(int id) {
        return changeShiftsDao.load(id);
    }

    @Override
    public Pager<ChangeShifts> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        String startTime = "", endTime = "";
        int s_id = 0;
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
                } else if (jsonObject.get("name").equals("storeId")){
                    s_id = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setOrderColumn("cs.id");
            params.setOrderDir("desc");
            if(s_id != 0){
                storeId = s_id;
            }
            List<Store> stores = storeDao.findByPid(storeId);
            if(stores != null && stores.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (Store store : stores) {
                    sb.append(store.getId());
                }
                sb.deleteCharAt(sb.length() - 1);
                params.setStringParam1(sb.toString());
            }
            return changeShiftsDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
