package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.OrderGoodsInfo;

import java.util.List;

/**
 * 商品货流管理
 * Created by fy on 2017/1/28.
 */
public interface IOrderGoodsInfoDao extends  IBaseDao<OrderGoodsInfo>{

    /**
     * 根据订单id查询
     * @param id
     * @return
     */
     List<OrderGoodsInfo> findByGoodsTrafficId(int id);

    /**
     * 根据货流管理id查询
     * @param id
     * @return
     */
     List<OrderGoodsInfo> findByTrafficManageId(int id);
}
