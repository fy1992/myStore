package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;

import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
public interface IPermissionService {
    void add(Permission t);
    void del(int id);
    void update(Permission t);
    Permission get(int id);
    Permission load(int id);
    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @param type
     * @return
     */
    Pager<Permission> findByParams(String aDataSet, int storeId, int type);

    /**
     * 查询全部
     * @param storeId
     * @return
     */
    List<Permission> findAll(int storeId, int type);
}
