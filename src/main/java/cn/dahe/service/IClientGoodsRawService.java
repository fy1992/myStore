package cn.dahe.service;

import cn.dahe.model.ClientGoodsRaw;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/23.
 */
public interface IClientGoodsRawService {
    boolean add(ClientGoodsRaw t);
    void del(int id);
    void update(ClientGoodsRaw t);
    ClientGoodsRaw get(int id);
    ClientGoodsRaw load(int id);

    /**
     * 根据类别查询
     * @param categories
     * @return
     */
    List<ClientGoodsRaw> goodsRawListByCategories(int categories, int storeId);

    ClientGoodsRaw findByRawNo(String rawNo, int storeId);
}
