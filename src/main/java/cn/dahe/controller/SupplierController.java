package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import cn.dahe.model.User;
import cn.dahe.service.ISupplierService;
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
 * 供货商
 * Created by fy on 2017/1/23.
 */
@Controller
@RequestMapping("supplier")
public class SupplierController {
    private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
    @Resource
    private ISupplierService supplierService;

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getSupplierList() {
        return "supplier/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Supplier> getSupplierList(HttpSession session, String aDataSet) {
        logger.info("--- Supplier list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return supplierService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     *供应商添加
     * @param supplier
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addUser(Supplier supplier){
        AjaxObj json = new AjaxObj();
        supplierService.add(supplier);
        json.setMsg("供应商添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 供应商修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String editSupplier(@PathVariable int id, Model model){
        Supplier supplier = supplierService.get(id);
        model.addAttribute("supplier", supplier);
        return "supplier/edit";
    }

    /**
     *供应商修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editSupplier(Supplier supplier){
        AjaxObj json = new AjaxObj();
        supplierService.update(supplier);
        json.setMsg("供应商修改成功");
        json.setResult(1);
        return json;
    }
}