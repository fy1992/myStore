package cn.dahe.dao.impl;

import cn.dahe.dao.IUserDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2016/12/30.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
    @Override
    public User findByLoginName(String loginName) {
        String hql = "from User user where user.loginName = ?";
        return (User)this.queryByHql(hql, loginName);
    }

    @Override
    public Pager<User> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        int status = params.getStatus();
        Date startTime = params.getEndTime();
        Date endTime = params.getEndTime();
        int storeId = params.getIntParam1();
        String userInfo = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and user.registerDate >= ? ");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and user.registerDate <= ? ");
            list.add(endTime);
        }
        if(storeId != -1 && storeId != 0){
            hql.append(" and user.storeId = ?");
            list.add(storeId);
        }
        if(StringUtils.isNotBlank(userInfo)){
            hql.append(" and user.username like ?");
            list.add("%" + userInfo + "%");
        }
        hql.append(" and user.status = ?");
        list.add(status);
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<User> findAll(int storeId) {
        String hql = "from User user";
        if(storeId != -1){
            hql += " where user.storeId = ?";
        }
        return this.list(hql, storeId);
    }

    @Override
    public User findByStoreId(int storeId) {
        String hql = "from User user where user.storeId = ? and user.rank <> 3";
        return (User)this.queryByHql(hql, storeId);
    }
}
