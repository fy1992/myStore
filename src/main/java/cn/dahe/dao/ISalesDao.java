package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Sales;

import java.util.List;

/**
 * 导购员
 * Created by fy on 2017/1/25.
 */
public interface ISalesDao extends IBaseDao<Sales>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Sales> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 根据导购员编号查询
     * @param salesNo
     * @return
     */
    Sales findBySalesNo(String salesNo, int storeId);

    List<Sales> findAll(int storeId);
}
