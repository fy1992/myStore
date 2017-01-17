package cn.dahe.service;

import cn.dahe.model.OptLog;
import cn.dahe.model.User;

/**
 * Created by fy on 2017/1/13.
 */
public interface IOptLogService{
    void add(OptLog t);
    void del(int id);
    void update(OptLog t);
    OptLog get(int id);
    OptLog load(int id);
    /**
     * 添加操作日志
     * @param user
     * @param ip
     * @param msg
     * @param objectId
     */
    void addOptLog(User user, String ip, String msg, int objectId);
}
