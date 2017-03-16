package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.SalesCampaign;

/**
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
}
