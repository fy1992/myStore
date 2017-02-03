package cn.dahe.service.impl;

import cn.dahe.dao.IRoleDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Role;
import cn.dahe.service.IRoleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/2/3.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{
    @Resource
    private IRoleDao roleDao;
    @Override
    public void add(Role t) {
        roleDao.add(t);
    }

    @Override
    public void del(int id) {
        roleDao.delete(id);
    }

    @Override
    public void update(Role t) {
        roleDao.update(t);
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
}
