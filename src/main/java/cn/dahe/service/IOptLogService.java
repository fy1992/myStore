package cn.dahe.service;

import cn.dahe.model.OptLog;
import cn.dahe.model.User;

/**
 * Created by fy on 2017/1/13.
 */
public interface IOptLogService extends IBaseService<OptLog>{
    /**
     * 添加操作日志
     * @param user
     * @param ip
     * @param msg
     * @param objectId
     */
    void addOptLog(User user, String ip, String msg, int objectId);
}
