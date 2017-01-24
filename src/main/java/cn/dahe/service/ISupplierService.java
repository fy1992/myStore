package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;

/**
 * Created by fy on 2017/1/23.
 */
public interface ISupplierService {
    void add(Supplier t);
    void del(int id);
    void update(Supplier t);
    Supplier get(int id);
    Supplier load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Supplier> findByParams(String aDataSet, int storeId);
}
