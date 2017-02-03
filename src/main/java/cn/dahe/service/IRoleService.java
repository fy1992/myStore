package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Role;

/**
 * Created by fy on 2017/2/3.
 */
public interface IRoleService {
    void add(Role t);
    void del(int id);
    void update(Role t);
    Role get(int id);
    Role load(int id);
    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Role> findByParams(String aDataSet, int storeId);
}
