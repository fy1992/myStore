package cn.dahe.dao.impl;

import cn.dahe.dao.IUserDao;
import cn.dahe.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2016/12/30.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
    @Override
    public User findByLoginName(String loginName) {
        String hql = "from User user where user.loginName = ?";
        return (User)this.queryByHql(hql, loginName);
    }


}
