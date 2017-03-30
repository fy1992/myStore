package cn.dahe.service.impl;

import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IVipDao;
import cn.dahe.dao.IVipLevelDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.model.Vip;
import cn.dahe.model.VipLevel;
import cn.dahe.service.IVipService;
import cn.dahe.util.NumberUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Resource
    private IStoreDao storeDao;

    @Override
    public void add(Vip t) {
        t.setRegisterTime(new Date());
        VipLevel vipLevel = vipLevelDao.get(t.getVipLevelID());
        t.setVipLevelName(vipLevel.getName());
        Store store = storeDao.get(t.getStoreId());
        if(store != null){
            t.setStoreName(store.getName());
        }
        t.setRegisterTime(new Date());
        vipDao.addAndGetId4Integer(t);
    }

    @Override
    public void del(int id) {
        vipDao.delete(id);
    }

    @Override
    public void update(Vip t) {
        Vip vip = vipDao.get(t.getId());
        vip.setAddr(t.getAddr());
        vip.setEmail(t.getEmail());
        vip.setBalance(t.getBalance());
        vip.setCreateCardDate(t.getCreateCardDate());
        vip.setBirthday(t.getBirthday());
        vip.setDescription(t.getDescription());
        vip.setVipLevelID(t.getVipLevelID());
        VipLevel vipLevel = vipLevelDao.get(t.getVipLevelID());
        vip.setVipLevelName(vipLevel.getName());
        vip.setCredit(t.getCredit());
        vip.setDueDate(t.getDueDate());
        vip.setVipName(t.getVipName());
        vip.setQq(t.getQq());
        vip.setRebate(t.getRebate());
        vip.setPoint(t.getPoint());
        vip.setPhone(t.getPhone());
        vip.setPassword(t.getPassword());
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
        int status = 1, level = 0;
        String vipInfo = "";
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
                } else if (jsonObject.get("name").equals("level")) {
                    level = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("vipInfo")) {
                    vipInfo = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("vip.id");
            params.setOrderDir("desc");
            params.setStatus(status);
            params.setIntParam2(level);
            params.setStringParam1(vipInfo);
            return vipDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Vip findByOpenId(String openId) {
        return vipDao.findByOpenId(openId);
    }

    @Override
    public List<Vip> findByVipInfo(String params, int storeId) {
        Store store = storeDao.get(storeId);
        StringBuffer sids = new StringBuffer(storeId + ",");
        if(store != null){
            List<Store> storeList = storeDao.findByPid(store.getId());
            for(Store s : storeList){
                sids.append(s.getId() + ",");
            }
        }
        sids = sids.deleteCharAt(sids.length() - 1);
        return vipDao.findByVipInfo(params, sids.toString());
    }
}
