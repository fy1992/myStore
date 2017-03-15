package cn.dahe.service.impl;

import cn.dahe.dao.*;
import cn.dahe.dto.Pager;
import cn.dahe.model.*;
import cn.dahe.service.IPermissionService;
import cn.dahe.service.IStoreService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.NumberUtils;
import cn.dahe.util.SecurityUtil;
import cn.dahe.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fy on 2017/1/24.
 */
@Service("storeService")
public class StoreServiceImpl implements IStoreService{
    private static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    @Resource
    private IStoreDao storeDao;
    @Resource
    private IStoreGoodsTrafficDao storeGoodsTrafficDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IPermissionService permissionService;

    @Override
    public void add(Store t) {
        storeDao.add(t);
    }

    @Override
    public int add(Store t, User user, User currentUser) {
        Store store = storeDao.findByStoreNo(t.getStoreNo());
        if(store == null){
            User ur = userDao.findByLoginName(user.getUsername());
            if(ur != null){
                return 2;
            }
            if(currentUser.getRank() > 0){
                Store parent = storeDao.get(currentUser.getStoreId());
                t.setParent(parent);
            }
            t.setCreateDate(new Date());
            int storeId = storeDao.addAndGetId4Integer(t);

            //门店的供货设置
            StoreGoodsTraffic storeGoodsTraffic = new StoreGoodsTraffic();
            storeGoodsTraffic.setStoreId(storeId);
            storeGoodsTraffic.setStoreName(t.getName());
            storeGoodsTraffic.setPrepareStoreId(storeId);
            storeGoodsTraffic.setPrepareStoreName(store.getName());
            storeGoodsTraffic.setPreparePriceType(0);
            storeGoodsTraffic.setDifferentOpt(0);
            storeGoodsTraffic.setPayOnline(0);
            storeGoodsTrafficDao.add(storeGoodsTraffic);

            /*Role role = roleDao.get(roleId);*/
            String pStr;
            //根据是否是连锁店分配相应等级的权限t
            if(t.getMultiple() == 0){
                pStr = "3";
            }else{
                pStr = "2,3";
            }
            Set<Permission> permissionSet = new HashSet<>(permissionService.findAll(0, pStr));

            //门店的登录账号
            User u = new User();
            if(currentUser.getStoreId() == 0){
                u.setRank(1);
            }else{
                u.setRank(2);
            }
            u.setStatus(t.getStatus());
            u.setStoreId(storeId);
            u.setStoreName(t.getName());
            u.setPassword(SecurityUtil.MD5(user.getPassword()));
            u.setUsername(user.getUsername());
            u.setPermissions(permissionSet);
            u.setMobile(StringUtil.formatStr(user.getMobile()));
            u.setEmail(StringUtil.formatStr(user.getEmail()));
            u.setRegisterDate(new Date());
            u.setLoginName(user.getUsername());
            userDao.addAndGetId4Integer(u);
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int add(Store store, User user) {
        String storeNo = Long.toString(NumberUtils.getNo(4));
        store.setStoreNo(storeNo);
        User ur = userDao.findByLoginName(user.getUsername());
        if(ur != null){
            return 0;
        }
        store.setCreateDate(new Date());
        store.setStatus(1);
        store.setMultiple(0);
        store.setType(0);
        int storeId = storeDao.addAndGetId4Integer(store);

        //门店的供货设置
        StoreGoodsTraffic storeGoodsTraffic = new StoreGoodsTraffic();
        storeGoodsTraffic.setStoreId(storeId);
        storeGoodsTraffic.setStoreName(store.getName());
        storeGoodsTraffic.setPrepareStoreId(storeId);
        storeGoodsTraffic.setPrepareStoreName(store.getName());
        storeGoodsTraffic.setPreparePriceType(0);
        storeGoodsTraffic.setDifferentOpt(0);
        storeGoodsTraffic.setPayOnline(0);
        storeGoodsTrafficDao.add(storeGoodsTraffic);

        Set<Permission> permissionSet = new HashSet<>(permissionService.findAll(0, "3"));

        //门店的登录账号
        User u = new User();
        u.setRank(2);
        u.setStatus(store.getStatus());
        u.setStoreId(storeId);
        u.setStoreName(store.getName());
        u.setPassword(SecurityUtil.MD5(user.getPassword()));
        u.setUsername(user.getUsername());
        u.setPermissions(permissionSet);
        u.setMobile(StringUtil.formatStr(user.getMobile()));
        u.setEmail(StringUtil.formatStr(user.getEmail()));
        u.setRegisterDate(new Date());
        u.setLoginName(user.getUsername());
        userDao.addAndGetId4Integer(u);
        return 1;
    }

    @Override
    public void del(int id) {
        storeDao.delete(id);
    }

    @Override
    public void update(Store t, User user) {
        User u = userDao.findByStoreId(t.getId());
        //根据是否是连锁店分配相应等级的权限
        String pStr;
        if(t.getMultiple() == 0){
            pStr = "3";
        }else{
            pStr = "2,3";
        }
        Set<Permission> permissionSet = new HashSet<>(permissionService.findAll(0, pStr));
        u.setStatus(t.getStatus());
        u.setMobile(user.getMobile());
        u.setEmail(user.getEmail());
        u.setPermissions(permissionSet);
        userDao.update(u);
        Store store = storeDao.get(t.getId());
        store.setAddr(t.getAddr());
        store.setContact(t.getContact());
        store.setStatus(t.getStatus());
        store.setDescription(t.getDescription());
        store.setName(t.getName());
        store.setWorkTime(t.getWorkTime());
        store.setIndustry(t.getIndustry());
        store.setMultiple(t.getMultiple());
        storeDao.update(store);
    }

    @Override
    public Store get(int id) {
        return storeDao.get(id);
    }

    @Override
    public Store load(int id) {
        return storeDao.load(id);
    }

    @Override
    public Pager<Store> findByParams(String aDataSet, int storeId) {
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
                }else if (jsonObject.get("name").equals("status")) {
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
            params.setOrderColumn("store.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            return storeDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Store> findAll(int storeId, int removeId) {
        Store store = storeDao.get(storeId);
        if(store != null){
            Store parent = store.getParent();
            if(parent != null){
                storeId = parent.getId();
            }
        }
        return storeDao.findAll(storeId, removeId);
    }

    @Override
    public void add(StoreGoodsTraffic storeGoodsTraffic) {
        storeGoodsTrafficDao.add(storeGoodsTraffic);
    }

    @Override
    public void update(StoreGoodsTraffic storeGoodsTraffic) {
        storeGoodsTrafficDao.update(storeGoodsTraffic);
    }

    @Override
    public StoreGoodsTraffic findByStoreId(int id) {
        return storeGoodsTrafficDao.findByStoreId(id);
    }

    @Override
    public List<StoreGoodsTraffic> findAllStoreGoodsTraffic() {
        return storeGoodsTrafficDao.findAll();
    }

    @Override
    public void updateStoreGoodsTraffics(StoreGoodsTraffic storeGoodsTraffic) {
        StoreGoodsTraffic sgt = storeGoodsTrafficDao.get(storeGoodsTraffic.getId());
        sgt.setDifferentOpt(storeGoodsTraffic.getDifferentOpt());
        sgt.setPayOnline(storeGoodsTraffic.getPayOnline());
        sgt.setPreparePriceType(storeGoodsTraffic.getPreparePriceType());
        sgt.setPrepareStoreId(storeGoodsTraffic.getPrepareStoreId());
        Store store = storeDao.get(storeGoodsTraffic.getPrepareStoreId());
        if(store != null){
            sgt.setPrepareStoreName(store.getName());
        }else{
            sgt.setPrepareStoreName("");
        }
        storeGoodsTrafficDao.update(sgt);
    }
}
