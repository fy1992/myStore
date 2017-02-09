package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;

import java.util.List;

/**
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
     * @param storeId
     * @param type
     * @return
     */
    List<Permission> findAll(int storeId, int type);
}
