package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.model.Cashier;
import cn.dahe.model.User;
import cn.dahe.service.IEmployeeService;
import cn.dahe.util.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * 登录
 * Created by fy on 2016/12/30.
 */
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private IEmployeeService employeeService;

    /**
     * 登录页跳转
     * */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){
        logger.info("--- 登录页跳转 ---");
        model.addAttribute(new User());
        return "/login";
    }

    /**
     * 用户登录
     * */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj login(String loginName, String password){
        logger.info("-- 用户登录 --");
        String msg = "";
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, SecurityUtil.MD5(password));
        Subject subject = SecurityUtils.getSubject();
        AjaxObj json = new AjaxObj();
        try{
            subject.login(token);
            //认证通过
            if(subject.isAuthenticated()){
                json.setResult(1);
            }else{
                logger.info("--- 认证失败 ---");
                msg = "权限认证失败，请联系管理员";
                json.setResult(0);
            }
            return json;
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误";
            json.setResult(0);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            json.setResult(0);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定";
            json.setResult(0);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用";
            json.setResult(0);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期";
            json.setResult(0);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在";
            json.setResult(0);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权";
            json.setResult(0);
        }finally {
            json.setMsg(msg);
            logger.info(msg);
        }
        return json;
    }

    /**
     * 退出
     * */
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("msg", "您已安全退出");
        return "redirect:/login";
    }

    /**
     * 收银员登录
     * @param cashierNo
     * @param password
     * @return
     */
    @RequestMapping(value = "cashierLogin", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogin(String cashierNo, String password, HttpSession session){
        AjaxObj json = employeeService.cashierLogin(cashierNo, password);
        if(json.getResult() == 1){
            session.setAttribute("clientUser", json.getObject());
        }
        return json;
    }

    /**
     * 收银员退出
     * */
    @RequestMapping(value="/cashierLogout", method=RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogout(HttpSession session){
        AjaxObj json = new AjaxObj();
        session.removeAttribute("clientUser");
        json.setResult(1);
        json.setMsg("成功退出");
        return json;
    }

}
