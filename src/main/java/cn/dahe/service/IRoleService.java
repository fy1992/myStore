package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Role;

import java.util.List;

/**
 * Created by fy on 2017/2/3.
 */
public interface IRoleService {
    boolean add(Role t, String permissions);
    void del(int id);
    boolean update(Role t, String permissions);
    Role get(int id);
    Role load(int id);
    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Role> findByParams(String aDataSet, int storeId);

    /**
     * 查询全部
     * @param storeId
     * @return
     */
    List<Role> findAll(int storeId);
}
