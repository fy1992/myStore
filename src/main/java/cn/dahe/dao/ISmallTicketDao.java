package cn.dahe.dao;

import cn.dahe.model.SmallTicket;

import java.util.List;

/**
 * 后厨小票
 * Created by fy on 2017/1/13.
 */
public interface ISmallTicketDao extends IBaseDao<SmallTicket>{
    /**
     *根据名称查询
     * @param name
     * @param storeId
     * @return
     */
    SmallTicket findByName(String name, int storeId);

    /**
     *查询全部
     * @param storeId
     * @return
     */
    List<SmallTicket> findAll(int storeId);
}
