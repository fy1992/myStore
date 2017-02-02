package cn.dahe.service.impl;

import cn.dahe.dao.ITrafficManageDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;
import cn.dahe.service.ITrafficManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/2/1.
 */
@Service("trafficManageService")
public class TrafficManageServiceImpl implements ITrafficManageService{
    private static Logger logger = LoggerFactory.getLogger(TrafficManageServiceImpl.class);
    @Resource
    private ITrafficManageDao trafficManageDao;

    @Override
    public void add(TrafficManage t) {
        trafficManageDao.add(t);
    }

    @Override
    public void del(int id) {
        trafficManageDao.delete(id);
    }

    @Override
    public void update(TrafficManage t) {
        trafficManageDao.update(t);
    }

    @Override
    public TrafficManage get(int id) {
        return trafficManageDao.get(id);
    }

    @Override
    public TrafficManage load(int id) {
        return trafficManageDao.load(id);
    }

    @Override
    public Pager<TrafficManage> findByParams(String aDataSet, int storeId) {
        return null;
    }

    @Override
    public void importTrafficManageExcel(int storeId) {

    }
}
