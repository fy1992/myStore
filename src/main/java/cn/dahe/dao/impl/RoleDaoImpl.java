package cn.dahe.dao.impl;

import cn.dahe.dao.IRoleDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/2/3.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao{
    @Override
    public Pager<Role> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Role role where 1=1");
        List<Object> list = new ArrayList<>();
        int storeId = params.getIntParam1();
        if(storeId != -1){
            hql.append(" and role.storeId = ? ");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<Role> findAll(int storeId) {
        String hql = "from Role where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public Role findByRoleKey(String roleKey, int storeId) {
        String hql = "from Role where roleKey = ? and storeId = ?";
        return (Role)this.queryByHql(hql, new Object[]{roleKey, storeId});
    }
}
