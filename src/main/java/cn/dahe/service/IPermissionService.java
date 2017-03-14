package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;

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
     */
    Pager<Permission> findByParams(String aDataSet);

    /**
     * 查询全部
     */
    List<Permission> findAll(int type, String levels);

    /**
     * 所有目录
     * @param resourceType 资源类型
     * @param storeId 店面id
     * @param multiple 是否是连锁店
     */
    List<Permission> findByResourceType(int resourceType, int storeId, int multiple);

    List<Permission> findByRoleId(int roleId);
}
