package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;

/**
 *收银员
 * Created by fy on 2017/1/17.
 */
public interface ICashierDao extends IBaseDao<Cashier>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Cashier> findByParam(int start, int pageSize, Pager<Object> params);
}
