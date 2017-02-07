package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.SmallTicket;
import cn.dahe.model.User;

import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
public interface ISmallTicketService{
    void add(SmallTicket t);
    void del(int id);
    void update(SmallTicket t);
    SmallTicket get(int id);
    SmallTicket load(int id);
    /**
     * 添加
     * @param name
     * @param type
     * @param storeId
     */
    void add(String name, int type, int storeId);

    /**
     * 批量添加
     * @param smallTicketList
     * @param storeId
     */
    void add(String smallTicketList, int storeId);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<SmallTicket> findByParams(String aDataSet, int storeId);


    /**
     * 查询每个店铺下的重复
     * @param name
     * @param storeId
     * @return
     */
    SmallTicket findByName(String name, int storeId);

    /**
     * 查询每个店铺下所有的
     * @param storeId
     * @return
     */
    List<SmallTicket> findAll(int storeId);
}
