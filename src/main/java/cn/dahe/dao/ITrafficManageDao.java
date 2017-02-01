package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;

/**
 * 货流管理
 * Created by fy on 2017/1/30.
 */
public interface ITrafficManageDao extends IBaseDao<TrafficManage>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<TrafficManage> findByParam(int start, int pageSize, Pager<Object> params);
}
