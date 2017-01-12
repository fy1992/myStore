package cn.dahe.dao;

import cn.dahe.model.User;

/**
 * Created by fy on 2016/12/30.
 */
public interface IUserDao extends IBaseDao<User>{
    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);
}
