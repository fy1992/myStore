package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.User;

/**
 * Created by fy on 2016/12/30.
 */

public interface IUserService{
    void add(User t);
    void del(int id);
    void update(User t);
    User get(int id);
    User load(int id);
    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<User> findByParams(String aDataSet, int storeId);
}
