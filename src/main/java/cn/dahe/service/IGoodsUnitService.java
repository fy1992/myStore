package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.User;

import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsUnitService{
    void add(GoodsUnit t);
    void del(int id);
    void update(GoodsUnit t);
    GoodsUnit get(int id);
    GoodsUnit load(int id);
    /**
     * 添加
     * @param name
     * @param storeId
     */
    void add(String name, int storeId);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsUnit> findByParams(String aDataSet, int storeId);


    /**
     * 查询每个店铺下的重复
     * @param name
     * @param user
     * @return
     */
    GoodsUnit findByName(String name, User user);

    /**
     * 查询每个店铺下所有的
     * @return
     */
    List<GoodsUnit> findAll();

    /**
     * 修改
     * @param id
     * @param name
     */
    void update(int id, String name);
}
