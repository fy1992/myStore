package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.RegisterDto;
import cn.dahe.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 注册
 * Created by fy on 2017/2/17.
 */
@Controller
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private IRegisterService registerService;
    /**
     * 注册
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public AjaxObj register(RegisterDto registerDto){
        AjaxObj json = new AjaxObj();
        String password = registerDto.getPassword();
        String checkPassword = registerDto.getCheckPassword();
        if(!password.equals(checkPassword)){
            json.setMsg("两次密码输入不一致");
            json.setResult(0);
            return json;
        }
        registerService.register(registerDto);
        json.setResult(1);
        json.setMsg("注册成功");
        return json;
    }
}
