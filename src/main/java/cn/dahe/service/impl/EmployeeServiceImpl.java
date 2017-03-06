package cn.dahe.service.impl;

import cn.dahe.dao.ICashierDao;
import cn.dahe.dao.ISalesDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IUserDao;
import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.model.Store;
import cn.dahe.model.User;
import cn.dahe.service.IEmployeeService;
import cn.dahe.service.IStoreService;
import cn.dahe.util.ResourcesUtils;
import cn.dahe.util.SecurityUtil;
import cn.dahe.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private IUserDao userDao;
    @Resource
    private IStoreDao storeDao;
    @Override
    public boolean addCashier(Cashier t, User user) {
        Cashier c = cashierDao.findByCashierNo(t.getCashierNo());
        if(c != null){
            return false;
        }
        Store store = storeDao.get(user.getStoreId());
        t.setStoreId(store.getId());
        t.setPassword(SecurityUtil.MD5(t.getPassword()));
        cashierDao.add(t);

        User cashierUser = new User();
        cashierUser.setPassword(SecurityUtil.MD5(ResourcesUtils.getCashierPassword()));
        cashierUser.setRegisterDate(new Date());
        cashierUser.setStoreId(store.getId());
        cashierUser.setStatus(1);
        cashierUser.setLoginName(user.getLoginName() + ":" + t.getCashierNo());
        cashierUser.setUsername(user.getUsername());
        cashierUser.setStoreName(store.getName());
        cashierUser.setPermissionSet(t.getPermissionSet());
        cashierUser.setRank(3);
        userDao.add(cashierUser);
        return true;
    }

    @Override
    public void delCashier(int id) {
        cashierDao.delete(id);
    }


    @Override
    public void updateCashier(Cashier t) {
        Cashier c = getCashier(t.getId());
        c.setStatus(t.getStatus());
        c.setName(t.getName());

        cashierDao.update(c);
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
    public boolean addSales(Sales t, User user) {
        Sales s = salesDao.findBySalesNo(t.getSalesNo(), user.getStoreId());
        if(s == null){
            Sales sales = getSales(t.getId());
            sales.setPhone(t.getPhone());
            sales.setStatus(t.getStatus());
            sales.setSalesName(t.getSalesName());
            sales.setPercentage(t.getPercentage());
            sales.setPreMark(t.getPreMark());
            salesDao.add(sales);
            return true;
        }
        return false;
    }

    @Override
    public void delSales(int id) {
        salesDao.delete(id);
    }

    @Override
    public void updateSales(Sales t, User user) {
        Sales s = salesDao.findBySalesNo(t.getSalesNo(), user.getStoreId());
        s.setPercentage(t.getPercentage());
        s.setStatus(t.getStatus());
        s.setSalesName(t.getSalesName());
        s.setPhone(t.getPhone());
        s.setPreMark(t.getPreMark());
        s.setPreMarkTime(t.getPreMarkTime());
        salesDao.update(s);
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

    @Override
    public Cashier findByCashierNo(String cashierNo) {
        return cashierDao.findByCashierNo(cashierNo);
    }

    @Override
    public AjaxObj cashierLogin(String cashierNo, String password) {
        Cashier cashier = findByCashierNo(cashierNo);
        AjaxObj json = new AjaxObj();
        if(StringUtils.isBlank(cashierNo) || StringUtils.isBlank(password)){
            json.setResult(0);
            json.setMsg("用户名或密码不能为空");
            return json;
        }
        if(cashier == null){
            json.setResult(0);
            json.setMsg("很抱歉，您输入的工号不存在");
            return json;
        }
        if(!SecurityUtil.MD5(password).equals(cashier.getPassword())){
            json.setResult(0);
            json.setMsg("密码输入有误，请重新输入");
            return json;
        }
        if(cashier.getStatus() == 0){
            json.setResult(0);
            json.setMsg("很抱歉，该工号已停用");
            return json;
        }
        json.setResult(1);
        cashier.setPassword("");
        json.setObject(cashier);
        return json;
    }

    @Override
    public Sales findBySalesNo(String salesNo, int storeId) {
        return salesDao.findBySalesNo(salesNo, storeId);
    }
}
