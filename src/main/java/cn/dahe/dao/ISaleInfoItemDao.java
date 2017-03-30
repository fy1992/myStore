package cn.dahe.dao;

import cn.dahe.model.SaleInfoItem;

import java.util.List;

/**
 * 销售单据明细
 * Created by fy on 2017/3/30.
 */
public interface ISaleInfoItemDao extends IBaseDao<SaleInfoItem>{
    List<SaleInfoItem> findBySaleInfoId(int saleInfoId);
}
