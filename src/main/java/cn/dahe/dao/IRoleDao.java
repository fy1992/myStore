package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Role;

import java.util.List;

/**
 * 角色
 * Created by fy on 2017/2/3.
 */
public interface IRoleDao extends IBaseDao<Role>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Role> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 查询全部
     * @param storeId
     * @return
     */
    List<Role> findAll(int storeId);

    Role findByRoleKey(String roleKey, int storeId);
}
