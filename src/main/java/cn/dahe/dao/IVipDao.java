package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;

import java.util.List;


/**
 * Created by fy on 2017/3/15.
 */
public interface IVipDao extends IBaseDao<Vip>{
    /**
     * 带条件查询会员
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Vip> findByParam(int start, int pageSize, Pager<Object> params);

    Vip findByVipNo(String vipNo);

    List<Vip> findByStorId(int storeId);

    Vip findByOpenId(String openId);

    List<Vip> findByVipInfo(String params, String storeIds);
}
