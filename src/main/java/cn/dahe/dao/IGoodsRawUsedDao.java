package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRawUsed;

import java.util.Date;

/**
 * 原材料消耗
 * Created by fy on 2017/3/28.
 */
public interface IGoodsRawUsedDao extends IBaseDao<GoodsRawUsed>{
    /**
     * 带条件查询商品
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<GoodsRawUsed> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 根据原材料编号、消耗时间查询
     * @param rawNo
     * @param time
     * @param storeId
     * @return
     */
    GoodsRawUsed findByRawNoAndTime(String rawNo, String time, int storeId);
}
