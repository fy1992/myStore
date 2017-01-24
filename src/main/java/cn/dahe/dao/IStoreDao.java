package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Store;

/**
 * Created by fy on 2017/1/24.
 */
public interface IStoreDao extends IBaseDao<Store>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Store> findByParam(int start, int pageSize, Pager<Object> params);
}
