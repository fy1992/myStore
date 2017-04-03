package cn.dahe.dao;

import cn.dahe.model.SaleCount;

import java.util.Date;
import java.util.List;

/**
 * 销售统计
 * Created by 冯源 on 2017/3/22.
 */
public interface ISaleCountDao extends IBaseDao<SaleCount>{
    /**
     * 获取某一段时间的销售统计
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleCount> findByDay(Date startTime, Date endTime, int storeId);
}
