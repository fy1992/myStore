package cn.dahe.dao.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements IPermissionDao{
    @Override
    public Pager<Permission> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Permission permission where 1=1");
        List<Object> list = new ArrayList<>();
        int storeId = params.getIntParam1();
        int type = params.getIntParam2();
        hql.append(" and permission.type = ?");
        list.add(type);
        if(storeId != -1){
            hql.append(" and permission.storeId = ? ");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<Permission> findAll(int storeId, int type) {
        String hql = "from Permission where storeId = ? and type = ?";
        return this.list(hql, new Object[]{storeId, type});
    }
}
