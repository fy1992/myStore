package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Store;

import java.util.List;

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

    /**
     * 查询全部点店面
     * @param storeId
     * @return
     */
    List<Store> findAll(int storeId, int removeId);

    /**
     * 根据店面编号查询
     * @param storeNo
     * @return
     */
    Store findByStoreNo(String storeNo);

    List<Store> findByPid(int storeId);
}
