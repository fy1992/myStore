package cn.dahe.service;

import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.SaleCount;
import cn.dahe.model.SaleInfo;
import cn.dahe.model.SaleInfoItem;

import java.util.Date;
import java.util.List;

/**
 * 销售单据
 * Created by fy on 2017/3/30.
 */
public interface ISaleInfoService {
    boolean add(SaleInfo t);
    boolean add(ClientDataDto t, Cashier cashier);
    void del(int id);
    void update(SaleInfo t);
    SaleInfo get(int id);
    SaleInfo load(int id);

    /**
     * 添加营业额
     * @param saleCount
     */
    void addSaleCount(SaleCount saleCount);

    /**
     * 删除
     * @param id
     */
    void delSaleCount(int id);

    /**
     * 按时间查询营业额
     * @param storeId
     * @param countDate
     * @return
     */
    SaleCount getSaleCount(int storeId, Date countDate);
    /**
     * 销售列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<SaleInfo> saleInfoList(String aDataSet, int storeId);

    List<SaleInfoItem> findBySaleId(int saleId);

    /**
     * 按参数查询单据
     * @param info
     * @param startTime
     * @param endTime
     * @param storeId
     * @return
     */
    List<SaleInfo> saleInfoList(String info, String startTime, String endTime, int storeId);

    /**
     * 按时间查询营业情况
     * @param StartTime
     * @param endTime
     * @param storeId
     * @return
     */
    List<SaleCount> findByDay(String StartTime, String endTime, int storeId);
}
