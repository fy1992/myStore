package cn.dahe.service.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dao.IRoleDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.service.IPermissionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService{
    @Resource
    private IPermissionDao permissionDao;
    @Resource
    private IRoleDao roleDao;

    @Override
    public boolean add(Permission t) {
        Permission p = permissionDao.findByPerKey(t.getPerKey());
        if(p == null){
            permissionDao.add(t);
            return true;
        }
        return false;
    }

    @Override
    public void del(int id) {
        permissionDao.delete(id);
    }

    @Override
    public boolean update(Permission t) {
        Permission p = permissionDao.get(t.getId());
        if(!t.getPerKey().equals(p.getPerKey())){
            Permission r = permissionDao.findByPerKey(t.getPerKey());
            if(r != null){
                return false;
            }
        }
        p.setName(t.getName());
        p.setDescription(t.getDescription());
        p.setType(t.getType());
        p.setUrl(t.getUrl());
        p.setParentId(t.getParentId());
        p.setPerKey(t.getPerKey());
        permissionDao.update(t);
        return true;
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
    public Pager<Permission> findByParams(String aDataSet) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int type = -1;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("type")) {
                    type = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("permission.id");
            params.setOrderDir("desc");
            params.setIntParam1(type);
            return permissionDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Permission> findAll(int type, String levels) {
        String[] typesStrArr = levels.split(",");
        Integer[] typesIntegerArr = new Integer[typesStrArr.length];
        for(int i = 0, len = typesStrArr.length; i < len; i++){
            if(StringUtils.isNotBlank(typesStrArr[i])){
                typesIntegerArr[i] = Integer.parseInt(typesStrArr[i]);
            }
        }
        return permissionDao.findAll(type, typesIntegerArr);
    }

    @Override
    public List<Permission> findByResourceType(int resourceType, int storeId, int multiple) {
        return permissionDao.findByResourceType(resourceType, storeId, multiple);
    }

    @Override
    public List<Permission> findByRoleId(int roleId) {
        Role role = roleDao.get(roleId);
        if(role == null){
            return null;
        }
        List<Permission> list = new ArrayList<>();
        list.addAll(role.getPermissions());
        return list;
    }
}
