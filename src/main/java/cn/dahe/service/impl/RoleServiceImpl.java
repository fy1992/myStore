package cn.dahe.service.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dao.IRoleDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.service.IRoleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public boolean add(Role t, String permissions) {
        String roleKey = t.getRoleKey();
        Role r = roleDao.findByRoleKey(roleKey, t.getStoreId());
        if(r == null){
            String[] permissionArr = permissions.split(",");
            Set<Permission> set = new HashSet<>();
            for(int i = 0, len = permissionArr.length; i < len; i++){
                Permission permission = permissionDao.get(Integer.parseInt(permissionArr[i]));
                set.add(permission);
            }
            t.setPermissionSet(set);

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
        if(!t.getRoleKey().equals(role.getRoleKey())){
            Role r = roleDao.findByRoleKey(t.getRoleKey(), t.getStoreId());
            if(r != null){
                return false;
            }
        }
        String[] permissionArr = permissions.split(",");
        Set<Permission> set = new HashSet<>();
        for(int i = 0, len = permissionArr.length; i < len; i++){
            Permission permission = permissionDao.get(Integer.parseInt(permissionArr[i]));
            set.add(permission);
        }
        role.setPermissionSet(set);
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
            params.setIntParam4(storeId);
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
