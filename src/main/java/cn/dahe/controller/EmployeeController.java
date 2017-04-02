package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Cashier;
import cn.dahe.model.ChangeShifts;
import cn.dahe.model.Permission;
import cn.dahe.model.Sales;
import cn.dahe.model.User;
import cn.dahe.service.IChangeShiftsService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

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
    @Resource
    private IChangeShiftsService changeShiftsService;
    //========================================cashier begin========================================================
    /**
     * 收银员列表页查询
     * */
    @RequestMapping(value = "cashierList", method = RequestMethod.GET)
    public String cashierList(){
        return "employee/cashierList";
    }

    /**
     * 收银员列表页查询
     * */
    @RequestMapping(value = "cashierList", method = RequestMethod.POST)
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
    public AjaxObj cashierAdd(Cashier cashier, String permissionIds, int roleId, HttpSession session, HttpServletRequest request){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        request.getParameter("permissionIds");
        employeeService.addCashier(cashier, user, permissionIds, roleId);
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
        Set<Permission> permissionSet = cashier.getPermissions();
        StringBuffer permissionSb = new StringBuffer();
        permissionSet.forEach(permission -> {
            permissionSb.append(permission.getId() + ",");
        });
        if(permissionSb.length() > 0){
            permissionSb.deleteCharAt(permissionSb.length() - 1);
        }
        model.addAttribute("permissions", permissionSb.toString());
        return "employee/cashierEdit";
    }

    /**
     * 收银员编辑
     * @param cashier
     * @return AjaxObj
     */
    @RequestMapping(value = "cashierEdit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj cashierEdit(Cashier cashier, String permissionIds, int roleId){
        AjaxObj json = new AjaxObj();
        employeeService.updateCashier(cashier, permissionIds, roleId);
        json.setMsg("收银员编辑成功");
        json.setResult(1);
        return json;
    }

    /**
     * 所有收银员
     * @return
     */
    @RequestMapping(value = "allCashierList", method = RequestMethod.POST)
    @ResponseBody
    public List<Cashier> cashierList(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return employeeService.findAllCashier(user.getStoreId());
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
        json.setMsg("导购员添加成功");
        json.setResult(1);
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
    public AjaxObj salesEdit(Sales sales){
        AjaxObj json = new AjaxObj();
        employeeService.updateSales(sales);
        json.setMsg("导购员编辑成功");
        json.setResult(1);
        return json;
    }
    //========================================sales end========================================================
    //========================================交接班 begin========================================================
    /**
     * 交换班列表页查询
     * */
    @RequestMapping(value = "/changeShiftsList", method = RequestMethod.GET)
    public String changeShiftsList(){
        return "employee/changeShiftsList";
    }

    /**
     * 交换班列表页查询
     * */
    @RequestMapping(value = "/changeShiftsList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<ChangeShifts> changeShiftsList(HttpSession session, String aDataSet){
        logger.info("--- changeShifts list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return changeShiftsService.findByParams(aDataSet, user.getStoreId());
    }

    //========================================交接班 end========================================================
}
