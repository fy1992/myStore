package cn.dahe.dao.impl;

import cn.dahe.dao.ISysMenuDao;
import cn.dahe.model.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/2/27.
 */
@Repository("sysMenuDao")
public class SysMenuDao extends BaseDaoImpl<SysMenu> implements ISysMenuDao{
    @Override
    public List<SysMenu> queryAllMenu() {
        String hql = "from SysMenu";
        return this.list(hql);
    }

    @Override
    public SysMenu queryByName(String name) {
        String hql = "from SysMenu where name = ?";
        return (SysMenu)this.queryByHql(hql, name);
    }
}
