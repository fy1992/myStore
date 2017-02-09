package cn.dahe.service.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.service.IPermissionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService{
    @Resource
    private IPermissionDao permissionDao;

    @Override
    public void add(Permission t) {
        permissionDao.add(t);
    }

    @Override
    public void del(int id) {
        permissionDao.delete(id);
    }

    @Override
    public void update(Permission t) {
        permissionDao.update(t);
    }

    @Override
    public Permission get(int id) {
        return permissionDao.get(id);
    }

    @Override
    public Permission load(int id) {
        return permissionDao.load(id);
    }

    @Override
    public Pager<Permission> findByParams(String aDataSet, int storeId, int type) {
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
            params.setOrderColumn("permission.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setIntParam2(type);
            return permissionDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Permission> findAll(int storeId, int type) {
        return permissionDao.findAll(storeId, type);
    }
}
