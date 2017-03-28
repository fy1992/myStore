package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.SalesCampaign;

import java.util.List;

/**
 * 促销活动
 * Created by fy on 2017/3/16.
 */
public interface ISalesCampaignDao extends IBaseDao<SalesCampaign>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<SalesCampaign> findByParam(int start, int pageSize, Pager<Object> params);

    List<SalesCampaign> findAll(int storeId);

    /**
     * 查询有优惠券的优惠活动
     * @param storeId
     * @return
     */
    List<SalesCampaign> findByHasCoupon(int storeId);
}
