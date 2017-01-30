package cn.dahe.dao.impl;

import cn.dahe.dao.ITrafficManagerDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/30.
 */
@Repository("trafficManagerDao")
public class TrafficManagerDaoImpl extends BaseDaoImpl<TrafficManage> implements ITrafficManagerDao{
    @Override
    public Pager<TrafficManage> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
