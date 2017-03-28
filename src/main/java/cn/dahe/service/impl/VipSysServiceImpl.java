package cn.dahe.service.impl;

import cn.dahe.dao.IVipSysDao;
import cn.dahe.model.VipSys;
import cn.dahe.service.IVipSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/28.
 */
@Service("vipSysService")
public class VipSysServiceImpl implements IVipSysService{
    private Logger logger = LoggerFactory.getLogger(VipSysServiceImpl.class);
    @Resource
    private IVipSysDao vipSysDao;
    @Override
    public void add(VipSys t) {
        vipSysDao.add(t);
    }

    @Override
    public void del(int id) {
        vipSysDao.delete(id);
    }

    @Override
    public void update(VipSys t) {
        vipSysDao.update(t);
    }

    @Override
    public VipSys get(int id) {
        return vipSysDao.get(id);
    }

    @Override
    public VipSys load(int id) {
        return vipSysDao.load(id);
    }

    @Override
    public VipSys findByStoreId(int storeId) {
        return vipSysDao.findByStoreId(storeId);
    }
}
