package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.User;

import java.util.List;

/**
 * 后台用户
 * Created by fy on 2016/12/30.
 */
public interface IUserDao extends IBaseDao<User>{
    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 带条件查询用户
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<User> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 查询所有用户
     * @param storeId
     * @return
     */
    List<User> findAll(int storeId);
}
