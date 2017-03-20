package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.ChangeShifts;

import java.util.List;

/**
 * Created by fy on 2017/3/20.
 */
public interface IChangeShiftsService {
    int add(ChangeShifts t);
    void del(int id);
    void update(ChangeShifts t, int num, double price);
    void logout(Cashier cashier);
    ChangeShifts get(int id);
    ChangeShifts load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<ChangeShifts> findByParams(String aDataSet, int storeId);
}
