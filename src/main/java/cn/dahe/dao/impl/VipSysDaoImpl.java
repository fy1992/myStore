package cn.dahe.dao.impl;


import cn.dahe.dao.IVipSysDao;
import cn.dahe.model.VipSys;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/3/28.
 */
@Repository("vipSysDao")
public class VipSysDaoImpl extends BaseDaoImpl<VipSys> implements IVipSysDao{
    @Override
    public VipSys findByStoreId(int storeId) {
        String hql = "from VipSys where storeId = ?";
        return (VipSys)this.queryByHql(hql, storeId);
    }
}
