package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.SemifinishedItem;

/**
 * Created by 冯源 on 2017/4/8.
 */
public interface ISemifinishedItemService {

    boolean add(SemifinishedItem t);

    /**
     * 半成品记录
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<SemifinishedItem> semifinishedList(String aDataSet, int storeId);

}
