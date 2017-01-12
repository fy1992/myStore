package cn.dahe.service;

import cn.dahe.model.User;

/**
 * Created by fy on 2016/12/30.
 */

public interface IUserService extends IBaseService<User>{
    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);
}
