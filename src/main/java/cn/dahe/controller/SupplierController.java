package cn.dahe.controller;

import cn.dahe.model.Supplier;
import cn.dahe.service.ISupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 供货商
 * Created by fy on 2017/1/23.
 */
@Controller
@RequestMapping("supplier")
public class SupplierController {
    private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
    /*@Resource
    private ISupplierService supplierService;*/

}
