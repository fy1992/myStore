package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;

import java.util.List;

/**
 * Created by fy on 2017/1/23.
 */
public interface ISupplierDao extends IBaseDao<Supplier>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Supplier> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 根据名称和店面id查询
     * @param name
     * @return
     */
    List<Supplier> findByName(String name);

    /**
     * 查询全部
     * @return
     */
    List<Supplier> findAll();

    /**
     * 根据编号查询
     * @param supplierNo
     * @return
     */
    Supplier findByNo(String supplierNo);
}
