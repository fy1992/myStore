package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.SalesCampaign;

import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
public interface ISalesCampaignService {
    boolean add(SalesCampaign t);
    void del(int id);
    void update(SalesCampaign t);
    SalesCampaign get(int id);
    SalesCampaign load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     */
    Pager<SalesCampaign> findByParams(String aDataSet, int storeId);

    List<SalesCampaign> findByHasCoupon(int storeId);
}
