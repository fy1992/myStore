package cn.dahe.service;

import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;
import cn.dahe.model.BadGoodsItem;
import cn.dahe.model.Cashier;

import java.util.List;

/**
 * Created by fy on 2017/3/20.
 */
public interface IBadGoodsService {
    int add(BadGoods t);
    void add(ClientDataDto clientDataDto, Cashier cashier);
    boolean del(int id);
    void update(BadGoods t);
    BadGoods get(int id);
    BadGoods load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<BadGoods> findByParams(String aDataSet, int storeId);

    List<BadGoodsItem> findByBadGoodsId(int badGoodsId);
}
