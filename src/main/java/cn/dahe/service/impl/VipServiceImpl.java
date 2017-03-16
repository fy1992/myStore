package cn.dahe.service.impl;

import cn.dahe.dao.IVipDao;
import cn.dahe.dao.IVipLevelDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;
import cn.dahe.model.VipLevel;
import cn.dahe.service.IVipService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fy on 2017/3/15.
 */
@Service("vipService")
public class VipServiceImpl implements IVipService{
    private Logger logger = LoggerFactory.getLogger(VipServiceImpl.class);
    @Resource
    private IVipDao vipDao;
    @Resource
    private IVipLevelDao vipLevelDao;

    @Override
    public void add(Vip t) {
        t.setRegisterTime(new Date());
        VipLevel vipLevel = vipLevelDao.get(t.getVipLevelID());
        t.setVipLevelName(vipLevel.getName());
        vipDao.addAndGetId4Integer(t);
    }

    @Override
    public void del(int id) {
        vipDao.delete(id);
    }

    @Override
    public void update(Vip t) {
        Vip vip = vipDao.findByVipNo(t.getVipNo());
        vipDao.update(vip);
    }

    @Override
    public Vip get(int id) {
        return vipDao.get(id);
    }

    @Override
    public Vip load(int id) {
        return vipDao.load(id);
    }

    @Override
    public Pager<Vip> findByParams(String aDataSet) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("vip.id");
            params.setOrderDir("desc");
            params.setStatus(status);
            return vipDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}