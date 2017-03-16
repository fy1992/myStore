package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.VipLevel;

/**
 * Created by 冯源 on 2017/3/16.
 */
public interface IVipLevelService {
    void add(VipLevel t);
    void del(int id);
    void update(VipLevel t);
    VipLevel get(int id);
    VipLevel load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @return
     */
    Pager<VipLevel> findByParams(String aDataSet, int storeId);
}
