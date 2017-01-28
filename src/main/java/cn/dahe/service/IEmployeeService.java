package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.model.User;

/**
 * 员工 （收银员、导购员）
 * Created by fy on 2017/1/27.
 */
public interface IEmployeeService {
    void addCashier(Cashier t, User user);
    void delCashier(int id);
    void updateCashier(Cashier t);
    Cashier getCashier(int id);
    Cashier loadCashier(int id);

    void addSales(Sales t, User user);
    void delSales(int id);
    void updateSales(Sales t);
    Sales getSales(int id);
    Sales loadSales(int id);

    /**
     * 收银员列表
     * @param aDataSet
     * @param storeId
     * @param type  0 收银员 1  导购员
     * @return
     */
    Pager employeeList(String aDataSet, int storeId, int type);
}
