package cn.dahe.dao;

import cn.dahe.model.BadGoodsItem;

import java.util.List;

/**
 * 客户端订单明细
 * Created by fy on 2017/3/16.
 */
public interface IBadGoodsItemDao extends IBaseDao<BadGoodsItem>{

    /**
     * 根据订单id查询
     * @param badGoodsId
     * @return
     */
    List<BadGoodsItem> findByBadGoodsId(int badGoodsId);
}
