package cn.dahe.service.impl;

import cn.dahe.dao.IVipLevelDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.VipLevel;
import cn.dahe.service.IVipLevelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 冯源 on 2017/3/16.
 */
@Service("vipLevelService")
public class VipLevelServiceImpl implements IVipLevelService{
    @Resource
    private IVipLevelDao vipLevelDao;

    @Override
    public void add(VipLevel t) {
        vipLevelDao.add(t);
    }

    @Override
    public void del(int id) {
        vipLevelDao.delete(id);
    }

    @Override
    public void update(VipLevel t) {
        vipLevelDao.update(t);
    }

    @Override
    public VipLevel get(int id) {
        return vipLevelDao.get(id);
    }

    @Override
    public VipLevel load(int id) {
        return vipLevelDao.load(id);
    }

    @Override
    public Pager<VipLevel> findByParams(String aDataSet, int storeId) {
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
            params.setOrderColumn("vipLevel.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            return vipLevelDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
