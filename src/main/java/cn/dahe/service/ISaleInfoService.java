package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.SaleInfo;
import cn.dahe.model.SaleInfoItem;

import java.util.List;

/**
 * Created by fy on 2017/3/30.
 */
public interface ISaleInfoService {
    boolean add(SaleInfo t);
    void del(int id);
    void update(SaleInfo t);
    SaleInfo get(int id);
    SaleInfo load(int id);
    /**
     * 销售列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<SaleInfo> saleInfoList(String aDataSet, int storeId);

    List<SaleInfoItem> findBySaleId(int saleId);
}
