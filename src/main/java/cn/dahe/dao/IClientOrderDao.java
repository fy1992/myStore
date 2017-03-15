package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;

import java.util.List;

/**
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
     * 订单好查询
     * @param clientOrderNo
     * @return
     */
    ClientOrder findByClientOrderNo(String clientOrderNo);

    /**
     * 会员编号查询
     * @param vipNo
     * @return
     */
    List<ClientOrder> findByVipNo(String vipNo);

    List<ClientOrder> findByStoreId(int storeId);
}
