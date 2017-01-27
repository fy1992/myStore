package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.User;
import cn.dahe.service.IUserService;
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
 * 后台用户相关
 * Created by fy on 2017/1/17.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserList(){
        return "user/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<User> getUserList(HttpSession session, String aDataSet){
        logger.info("--- user list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return userService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 用户添加
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        model.addAttribute("store", user.getStoreId());
        return "user/add";
    }

    /**
     *用户添加
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addUser(User user){
        AjaxObj json = new AjaxObj();
        if(user.getStoreId() == 0){
            json.setResult(0);
            json.setMsg("请先添加门店信息");
        }
        userService.add(user);
        json.setMsg("用户添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 用户修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String editUser(@PathVariable int id,  Model model){
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    /**
     *用户修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editUser(User user){
        AjaxObj json = new AjaxObj();
        if(user.getStoreId() == 0){
            json.setResult(0);
            json.setMsg("请先添加门店信息");
        }
        userService.update(user);
        json.setMsg("用户修改成功");
        json.setResult(1);
        return json;
    }

}
