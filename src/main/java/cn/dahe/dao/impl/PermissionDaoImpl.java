package cn.dahe.dao.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
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
        int type = params.getIntParam1();
        if(type != -1 && type != 2){
            hql.append(" and permission.type = ?");
            list.add(type);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<Permission> findAll(int type) {
        String hql = "from Permission where type = ?";
        return this.list(hql, type);
    }

    @Override
    public List<Permission> findByPid(int parentId) {
        String hql = "from Permission where parentId = ?";
        return list(hql, parentId);
    }

    @Override
    public Permission findByPerKey(String perKey) {
        String hql = "from Permission where perKey = ?";
        return (Permission)this.queryByHql(hql, perKey);
    }

    @Override
    public List<Permission> findByLevel(int level){
        String hql = "from Permission where level = ?";
        return this.list(hql, level);
    }
}
