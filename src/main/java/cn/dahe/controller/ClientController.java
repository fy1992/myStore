package cn.dahe.controller;

import cn.dahe.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 客户端请求
 * Created by fy on 2017/2/6.
 */
@Controller
@RequestMapping("client")
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Resource
    private IEmployeeService employeeService;

}
