package cn.dahe.service.impl;

import cn.dahe.dao.ISalesCampaignDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SalesCampaign;
import cn.dahe.model.Store;
import cn.dahe.service.ISalesCampaignService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Service("salesCampaignService")
public class SalesCampaignServiceImpl implements ISalesCampaignService{
    private static Logger logger = LoggerFactory.getLogger(SalesCampaignServiceImpl.class);

    @Resource
    private ISalesCampaignDao salesCampaignDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public boolean add(SalesCampaign t) {
        Store store = storeDao.get(t.getStoreId());
        if(store != null){
            t.setStoreName(store.getName());
        }
        t.setOverdue(1);
        int id = salesCampaignDao.addAndGetId4Integer(t);
        return id != 0;
    }

    @Override
    public void del(int id) {
        salesCampaignDao.delete(id);
    }

    @Override
    public void update(SalesCampaign t) {
        salesCampaignDao.update(t);
    }

    @Override
    public SalesCampaign get(int id) {
        return salesCampaignDao.get(id);
    }

    @Override
    public SalesCampaign load(int id) {
        return salesCampaignDao.load(id);
    }

    @Override
    public Pager<SalesCampaign> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int overdue = -1, type = -1;
        String name = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("overdue")) {
                    overdue = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("type")) {
                    type = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("name")) {
                    name = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("sc.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setIntParam2(type);
            params.setIntParam3(overdue);
            params.setStringParam1(name);
            return salesCampaignDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalesCampaign> findByHasCoupon(int storeId) {
        return salesCampaignDao.findByHasCoupon(storeId);
    }
}
