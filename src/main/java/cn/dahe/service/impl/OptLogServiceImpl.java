package cn.dahe.service.impl;

import cn.dahe.dao.IOptLogDao;
import cn.dahe.model.OptLog;
import cn.dahe.model.User;
import cn.dahe.service.IOptLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fy on 2017/1/13.
 */
@Service("optLogService")
public class OptLogServiceImpl implements IOptLogService{
    @Resource
    private IOptLogDao optLogDao;

    @Override
    public void add(OptLog t) {
        optLogDao.add(t);
    }

    @Override
    public void del(int id) {
        optLogDao.delete(id);
    }

    @Override
    public void update(OptLog t) {
        optLogDao.update(t);
    }

    @Override
    public OptLog get(int id) {
        return optLogDao.get(id);
    }

    @Override
    public OptLog load(int id) {
        return optLogDao.load(id);
    }

    @Override
    public void addOptLog(User user, String ip, String msg, int objectId) {
        optLogDao.add(new OptLog(new Date(), ip, msg, user.getId(),
                user.getLoginName(), user.getStoreId(),
                user.getStoreName(), objectId));
    }

}
