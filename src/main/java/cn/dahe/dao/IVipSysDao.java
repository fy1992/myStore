package cn.dahe.dao;

import cn.dahe.model.VipSys;

/**
 * 会员制度
 * Created by fy on 2017/3/28.
 */
public interface IVipSysDao extends IBaseDao<VipSys>{
    VipSys findByStoreId(int storeId);
}
