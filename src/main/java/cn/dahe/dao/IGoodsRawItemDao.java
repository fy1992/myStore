package cn.dahe.dao;

import cn.dahe.model.GoodsRawItem;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/26.
 */
public interface IGoodsRawItemDao extends IBaseDao<GoodsRawItem>{
    /**
     * 根据订单id查询
     * @return
     */
    List<GoodsRawItem> findByGoodsId(int goodsId);
}
