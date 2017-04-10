package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;

import java.util.List;

/**
 * 客户端订单
 * Created by fy on 2017/3/15.
 */
public interface IClientOrderDao extends IBaseDao<ClientOrder>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<ClientOrder> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 订单号查询
     * @param clientOrderNo
     * @param storeId
     * @return
     */
    ClientOrder findByClientOrderNo(String clientOrderNo, int storeId);

    /**
     * 会员编号查询
     * @param vipNo
     * @return
     */
    List<ClientOrder> findByVipNo(String vipNo);

    /**
     * 根据状态获取订单
     * @param storeId
     * @param status
     * @return
     */
    List<ClientOrder> findByStoreId(int storeId, String status);

    List<ClientOrder> findByOpenId(String openId);
}
