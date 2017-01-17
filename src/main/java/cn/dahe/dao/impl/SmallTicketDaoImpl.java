package cn.dahe.dao.impl;

import cn.dahe.dao.ISmallTicketDao;
import cn.dahe.model.SmallTicket;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("smallTicketDao")
public class SmallTicketDaoImpl extends BaseDaoImpl<SmallTicket> implements ISmallTicketDao{
    @Override
    public SmallTicket findByName(String name, int storeId) {
        String hql = "from SmallTicket where name = ? and storeId = ?";
        return (SmallTicket)queryByHql(hql, new Object[]{name, storeId});
    }

    @Override
    public List<SmallTicket> findAll() {
        String hql = "from SmallTicket";
        return list(hql);
    }
}
