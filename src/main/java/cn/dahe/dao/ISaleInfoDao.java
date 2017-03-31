package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.SaleInfo;

import java.util.List;

/**
 * 销售单据
 * Created by fy on 2017/3/30.
 */
public interface ISaleInfoDao extends IBaseDao<SaleInfo>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<SaleInfo> findByParam(int start, int pageSize, Pager<Object> params);

    List<SaleInfo> saleInfoList(Pager<Object> params);
}
