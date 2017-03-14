package cn.dahe.service;

import cn.dahe.model.ClientGoods;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/14.
 */
public interface IClientGoodsService {
    boolean add(ClientGoods t);
    void del(int id);
    void update(ClientGoods t);
    ClientGoods get(int id);
    ClientGoods load(int id);

    /**
     * 根据类别查询
     * @param categories
     * @return
     */
    List<ClientGoods> goodsListByCategories(int categories);
}
