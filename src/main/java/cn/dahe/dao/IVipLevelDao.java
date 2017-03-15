package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.VipLevel;

/**
 * Created by fy on 2017/3/16.
 */
public interface IVipLevelDao extends IBaseDao<VipLevel>{
    /**
     * 带条件查询会员等级
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<VipLevel> findByParam(int start, int pageSize, Pager<Object> params);
}
