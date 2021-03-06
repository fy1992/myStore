package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.OptLog;

/**
 * 操作日志
 * Created by fy on 2017/1/13.
 */
public interface IOptLogDao extends IBaseDao<OptLog>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<OptLog> findByParam(int start, int pageSize, Pager<Object> params);
}
