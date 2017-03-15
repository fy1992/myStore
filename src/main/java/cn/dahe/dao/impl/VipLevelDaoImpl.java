package cn.dahe.dao.impl;

import cn.dahe.dao.IVipLevelDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.VipLevel;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("vipLevelDao")
public class VipLevelDaoImpl extends BaseDaoImpl<VipLevel> implements IVipLevelDao{
    @Override
    public Pager<VipLevel> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
