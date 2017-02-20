package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import cn.dahe.model.User;
import cn.dahe.service.ISupplierService;
import cn.dahe.util.NumberUtils;
import cn.dahe.util.UploadsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Pager<Supplier> getSupplierList(String aDataSet) {
        logger.info("--- Supplier list begin ---");
        return supplierService.findByParams(aDataSet);
    }

    /**
     *供应商添加
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(){
        return "supplier/add";
    }

    /**
     *供应商添加
     * @param supplier
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addUser(Supplier supplier){
        logger.info(supplier.toString());
        AjaxObj json = new AjaxObj();
        boolean b = supplierService.add(supplier);
        if(b){
            json.setMsg("供应商添加成功");
            json.setResult(1);
        }else{
            json.setMsg("该供应商编码已存在");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 供应商修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
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

    /**
     *查询所有供应商
     */
    @RequestMapping(value = "allSupplier", method = RequestMethod.POST)
    @ResponseBody
    public List<Supplier> allSupplier(){
        List<Supplier> suppliers =  supplierService.findAll();
        if(suppliers == null){
            suppliers = new ArrayList<>();
        }
        return  suppliers;
    }

    /**
     * 导入excel添加
     * @param file
     * @return
     */
    @RequestMapping("importExcel")
    @ResponseBody
    public AjaxObj importExcel(MultipartFile file){
        AjaxObj json = new AjaxObj();
        if(file == null){
            json.setMsg("请选择文件上传");
            json.setResult(0);
            return json;
        }
        if(!UploadsUtils.checkFilePostfix(file.getOriginalFilename(), "xls")){
            json.setMsg("无效的文件类型，请上传xls类型的文件");
            json.setResult(0);
            return json;
        }
        if(file.getSize() > 3000000){
            json.setMsg("上传失败，文件大小大于3M");
            json.setResult(0);
            return json;
        }
        Map<String, Object> map = supplierService.importSupplierExcel(file);

        return json;
    }

    /**
     * 生成供应商编号
     * @return
     */
    @RequestMapping("newSupplierNo")
    @ResponseBody
    public AjaxObj newSupplierNo(){
        AjaxObj json = new AjaxObj();
        json.setMsg(Long.toString(NumberUtils.getNo(6)));
        json.setResult(1);
        return json;
    }
}