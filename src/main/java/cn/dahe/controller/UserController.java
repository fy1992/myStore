package cn.dahe.controller;

import cn.dahe.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 后台用户相关
 * Created by fy on 2017/1/17.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;


}
