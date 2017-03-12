package cn.dahe.service.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dao.IRoleDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.model.Store;
import cn.dahe.service.IRoleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fy on 2017/2/3.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IPermissionDao permissionDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public boolean add(Role t, String permissions) {
        String roleKey = t.getRoleKey();
        Role r = roleDao.findByRoleKey(roleKey, t.getStoreId());
        if(r == null){
            if(StringUtils.isNotBlank(permissions)){
                String[] permissionArr = permissions.split(",");
                Set<Permission> set = new HashSet<>();
                Set<Integer> idSet = new HashSet<>();
                for(int i = 0, len = permissionArr.length; i < len; i++){
                    Permission permission = permissionDao.get(Integer.parseInt(permissionArr[i]));
                    int pid = permission.getParentId();
                    idSet.add(pid);
                    set.add(permission);
                }
                idSet.forEach(i -> {
                    Permission permission = permissionDao.get(i);
                    if(permission != null){
                        set.add(permission);
                    }
                });
                t.setPermissions(set);
            }
            if(t.getStoreId() != 0){
                Store store = storeDao.get(t.getStoreId());
                t.setStoreName(store.getName());
            }
            roleDao.add(t);
            return true;
        }
        return false;
    }

    @Override
    public void del(int id) {
        roleDao.delete(id);
    }

    @Override
    public boolean update(Role t, String permissions) {
        Role role = roleDao.get(t.getId());
        if(StringUtils.isNotBlank(permissions)){
            String[] permissionArr = permissions.split(",");
            Set<Permission> set = new HashSet<>();
            Set<Integer> idSet = new HashSet<>();
            for(int i = 0, len = permissionArr.length; i < len; i++){
                Permission permission = permissionDao.get(Integer.parseInt(permissionArr[i]));
                int pid = permission.getParentId();
                idSet.add(pid);
                set.add(permission);
            }
            idSet.forEach(i -> {
                Permission permission = permissionDao.get(i);
                if(permission != null){
                    set.add(permission);
                }
            });
            role.setPermissions(set);
        }else{
            role.setPermissions(null);
        }
        role.setDescription(t.getDescription());
        role.setRoleName(t.getRoleName());
        role.setIsAsync(t.getIsAsync());
        role.setStatus(t.getStatus());
        role.setRoleKey(t.getRoleKey());
        roleDao.update(role);
        return true;
    }

    @Override
    public Role get(int id) {
        return roleDao.get(id);
    }

    @Override
    public Role load(int id) {
        return roleDao.load(id);
    }

    @Override
    public Pager<Role> findByParams(String aDataSet, int storeId) {
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
            params.setOrderColumn("role.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            return roleDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findAll(int storeId) {
        return roleDao.findAll(storeId);
    }
}
