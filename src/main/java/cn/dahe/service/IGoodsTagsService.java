package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.User;

import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsTagsService{
    void add(GoodsTags t);
    void del(int id);
    void update(GoodsTags t);
    GoodsTags get(int id);
    GoodsTags load(int id);
    /**
     * 查询每个店铺下的重复
     * @param name
     * @param user
     * @return
     */
    GoodsTags findByName(String name, User user);

    /**
     * 查询每个店铺下所有的
     * @param storeId
     * @return
     */
    List<GoodsTags> findAll(int storeId);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsTags> findByParams(String aDataSet, int storeId);

    /**
     * 添加
     * @param name
     * @param storeId
     */
    void add(String name, int storeId);

    /**
     * 修改
     * @param id
     * @param name
     */
    void update(int id, String name);
}
