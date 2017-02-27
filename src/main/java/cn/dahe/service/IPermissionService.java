package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.SysMenu;

import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
public interface IPermissionService {
    boolean add(Permission t);
    void del(int id);
    boolean update(Permission t);
    Permission get(int id);
    Permission load(int id);
    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Permission> findByParams(String aDataSet, int storeId);

    /**
     * 查询全部
     * @param storeId
     * @return
     */
    List<Permission> findAll(int storeId, int type);

    /**
     * 所有目录
     * @return
     */
    List<SysMenu> findAllSysMenu();

    SysMenu findByName(String name);
}
