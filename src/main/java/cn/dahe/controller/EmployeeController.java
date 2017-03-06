package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.Sales;
import cn.dahe.model.User;
import cn.dahe.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 员工
 * Created by fy on 2017/1/17.
 */
@Controller
@RequestMapping("server/employee")
public class EmployeeController {
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Resource
    private IEmployeeService employeeService;
    //========================================cashier begin========================================================
    /**
     * 收银员列表页查询
     * */
    @RequestMapping(value = "/cashierList", method = RequestMethod.GET)
    public String cashierList(){
        return "employee/cashierList";
    }

    /**
     * 收银员列表页查询
     * */
    @RequestMapping(value = "/cashierList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Cashier> cashierList(HttpSession session, String aDataSet){
        logger.info("--- cashier list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return employeeService.employeeList(aDataSet, user.getStoreId(), 0);
    }

    /**
     * 收银员添加页面路由
     * @return string
     */
    @RequestMapping(value = "cashierAdd", method = RequestMethod.GET)
    public String cashierAdd(){
        return "employee/cashierAdd";
    }

    /**
     * 收银员添加
     * @param cashier
     * @param session
     * @return AjaxObj
     */
    @RequestMapping(value = "cashierAdd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj cashierAdd(Cashier cashier, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        employeeService.addCashier(cashier, user);
        json.setMsg("收银员添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 收银员编辑页面路由
     * @return String
     */
    @RequestMapping(value = "cashierEdit/{id}", method = RequestMethod.GET)
    public String cashierEdit(@PathVariable int id, Model model){
        Cashier cashier = employeeService.getCashier(id);
        model.addAttribute("cashier", cashier);
        return "employee/cashierEdit";
    }

    /**
     * 收银员编辑
     * @param cashier
     * @return AjaxObj
     */
    @RequestMapping(value = "cashierEdit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj cashierEdit(Cashier cashier){
        AjaxObj json = new AjaxObj();
        employeeService.updateCashier(cashier);
        json.setMsg("收银员编辑成功");
        json.setResult(1);
        return json;
    }

    //========================================cashier end========================================================

    //========================================sales begin========================================================
    /**
     * 导购员列表页查询
     * */
    @RequestMapping(value = "/salesList", method = RequestMethod.GET)
    public String salesList(){
        return "employee/salesList";
    }

    /**
     * 导购员列表页查询
     * */
    @RequestMapping(value = "/salesList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Sales> salesList(HttpSession session, String aDataSet){
        logger.info("--- sales list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return employeeService.employeeList(aDataSet, user.getStoreId(), 1);
    }

    /**
     * 导购员添加页面路由
     * @return
     */
    @RequestMapping(value = "salesAdd", method = RequestMethod.GET)
    public String salesAdd(){
        return "employee/salesAdd";
    }

    /**
     * 导购员添加
     * @param sales
     * @param session
     * @return
     */
    @RequestMapping(value = "salesAdd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj salesAdd(Sales sales, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        employeeService.addSales(sales, user);
        json.setInfo("导购员添加成功");
        json.setStatus("y");
        return json;
    }

    /**
     * 导购员编辑页面路由
     * @return
     */
    @RequestMapping(value = "salesEdit/{id}", method = RequestMethod.GET)
    public String salesEdit(@PathVariable int id, Model model){
        Sales sales = employeeService.getSales(id);
        model.addAttribute("sales", sales);
        return "employee/salesEdit";
    }
    /**
     * 导购员编辑
     * @param sales
     * @return
     */
    @RequestMapping(value = "salesEdit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj salesEdit(Sales sales, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        employeeService.updateSales(sales, user);
        json.setMsg("导购员编辑成功");
        json.setResult(1);
        return json;
    }
    //========================================sales end========================================================
}
