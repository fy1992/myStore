package cn.dahe.service;

import cn.dahe.model.VipSys;

/**
 * 会员制度
 * Created by fy on 2017/3/28.
 */
public interface IVipSysService {
    void add(VipSys t);
    void del(int id);
    void update(VipSys t);
    VipSys get(int id);
    VipSys load(int id);

    VipSys findByStoreId(int storeId);
}
