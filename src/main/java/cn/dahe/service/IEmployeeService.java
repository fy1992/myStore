package cn.dahe.service;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.model.User;

import java.util.List;


/**
 * 员工 （收银员、导购员）
 * Created by fy on 2017/1/27.
 */
public interface IEmployeeService {
    boolean addCashier(Cashier t, User user, String permissionIds, int roleId);
    void delCashier(int id);
    void updateCashier(Cashier t, String permissionIds, int roleId);
    Cashier getCashier(int id);
    Cashier loadCashier(int id);
    /**
     * 根据工号查找
     * @param cashierNo
     * @return
     */
    Cashier findByCashierNo(int storeId, String cashierNo);

    /**
     *
     * @param cashierNo
     * @param password
     * @return
     */
    AjaxObj cashierLogin(int storeId, String cashierNo, String password);

    boolean addSales(Sales t, User user);
    void delSales(int id);
    void updateSales(Sales t);
    Sales getSales(int id);
    Sales loadSales(int id);

    /**
     * 根据工号查找
     * @param salesNo
     * @return
     */
    Sales findBySalesNo(String salesNo, int storeId);
    /**
     * 收银员列表
     * @param aDataSet
     * @param storeId
     * @param type  0 收银员 1  导购员
     * @return
     */
    Pager employeeList(String aDataSet, int storeId, int type);

    List<Sales> findAllSales(int storeId);
}
