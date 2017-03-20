package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.ChangeShifts;

/**
 * Created by fy on 2017/3/20.
 */
public interface IChangeShiftsDao extends IBaseDao<ChangeShifts>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<ChangeShifts> findByParam(int start, int pageSize, Pager<Object> params);
}
