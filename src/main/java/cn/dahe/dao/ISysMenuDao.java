package cn.dahe.dao;

import cn.dahe.model.SysMenu;

import java.util.List;

/**
 * Created by fy on 2017/2/27.
 */
public interface ISysMenuDao extends IBaseDao<SysMenu>{
    List<SysMenu> queryAllMenu();

    SysMenu queryByName(String name);
}
