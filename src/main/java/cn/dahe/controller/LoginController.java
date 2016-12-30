package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.model.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * Created by fy on 2016/12/30.
 */
@Controller
@RequestMapping("store/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录页跳转
     * */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "/login";
    }

    /**
     * 用户登录
     * */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj login(@Valid User user, Model model){
        AjaxObj json = new AjaxObj();

        return json;
    }

    /**
     * 退出
     * */
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(Model model){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        model.addAttribute("msg", "您已安全退出");
        return "/login";
    }
}
