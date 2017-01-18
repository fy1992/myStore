package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.dto.TasteDto;
import cn.dahe.model.TasteGroup;

/**
 * Created by fy on 2017/1/18.
 */
public interface ITasteGroupService {
    void add(TasteGroup t);
    void del(int id);
    void update(TasteGroup t);
    TasteGroup get(int id);
    TasteGroup load(int id);

    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<TasteDto> tasteList(String aDataSet, int storeId);
}
