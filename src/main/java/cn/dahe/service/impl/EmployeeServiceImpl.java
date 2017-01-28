package cn.dahe.service.impl;

import cn.dahe.dao.ICashierDao;
import cn.dahe.dao.ISalesDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.model.User;
import cn.dahe.service.IEmployeeService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public void addCashier(Cashier t, User user) {
        t.setStoreId(user.getStoreId());
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
    public void addSales(Sales t, User user) {
        t.setStoreId(user.getStoreId());
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
    public Pager employeeList(String aDataSet, int storeId, int type) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        String employeeInfo = "";
        JSONArray json = JSONArray.parseArray(aDataSet);
        int len = json.size();
        for (int i = 0; i < len; i++) {
            JSONObject jsonObject = (JSONObject) json.get(i);
            if (jsonObject.get("name").equals("iDisplayStart")) {
                start = (Integer) jsonObject.get("value");
            } else if (jsonObject.get("name").equals("iDisplayLength")) {
                pageSize = (Integer) jsonObject.get("value");
            } else if (jsonObject.get("name").equals("employeeInfo")) {
                employeeInfo = jsonObject.get("value").toString();
            } else if (jsonObject.get("name").equals("status")) {
                status = Integer.parseInt(jsonObject.get("value").toString());
            }
        }
        Pager<Object> params = new Pager<>();
        params.setStatus(status);
        params.setStringParam1(employeeInfo);
        params.setOrderDir("desc");
        params.setIntParam1(storeId);
        if(type == 1){
            params.setOrderColumn("sales.id");
            return salesDao.findByParam(start, pageSize, params);
        }else{
            params.setOrderColumn("cashier.id");
            return cashierDao.findByParam(start, pageSize, params);
        }
    }
}
