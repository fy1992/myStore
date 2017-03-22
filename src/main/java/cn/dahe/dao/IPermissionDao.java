package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;

import java.util.List;

/**
 * 权限资源
 * Created by fy on 2017/2/9.
 */
public interface IPermissionDao extends IBaseDao<Permission>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Permission> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 查询全部
     * @return
     */
    List<Permission> findAll(int type, Integer[] types);

    /**
     * 根据父id查询
     * @param parentId
     * @return
     */
    List<Permission> findByPid(int parentId);

    Permission findByPerKey(String perKey);

    List<Permission> findByResourceType(int resourceType, int storeId, int multiple);
}
