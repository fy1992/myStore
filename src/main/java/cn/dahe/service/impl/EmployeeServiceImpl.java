package cn.dahe.service.impl;

import cn.dahe.dao.ICashierDao;
import cn.dahe.dao.ISalesDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.service.IEmployeeService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/27.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{
    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Resource
    private ICashierDao cashierDao;
    @Resource
    private ISalesDao salesDao;

    @Override
    public void addCashier(Cashier t) {
        cashierDao.add(t);
    }

    @Override
    public void delCashier(int id) {
        cashierDao.delete(id);
    }


    @Override
    public void updateCashier(Cashier t) {
        cashierDao.update(t);
    }

    @Override
    public Cashier getCashier(int id) {
        return cashierDao.get(id);
    }

    @Override
    public Cashier loadCashier(int id) {
        return cashierDao.load(id);
    }

    @Override
    public void addSales(Sales t) {
        salesDao.add(t);
    }

    @Override
    public void delSales(int id) {
        salesDao.delete(id);
    }

    @Override
    public void updateSales(Sales t) {
        salesDao.update(t);
    }

    @Override
    public Sales getSales(int id) {
        return salesDao.get(id);
    }

    @Override
    public Sales loadSales(int id) {
        return salesDao.load(id);
    }

    @Override
    public Pager<T> employeeList(String aDataSet, int storeId, int type) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        String employeeInfo = "";
        Pager<Object> params = new Pager<>();
        params.setStatus(status);
        params.setStringParam1(employeeInfo);
        //return cashierDao.findByParam(start, pageSize, params);
        return null;
    }
}
