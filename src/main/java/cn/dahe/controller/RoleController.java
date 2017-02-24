package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Role;
import cn.dahe.model.User;
import cn.dahe.service.IRoleService;
import cn.dahe.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fy on 2017/2/3.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private IRoleService roleService;
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRoleList() {
        return "role/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Role> getRoleList(HttpSession session, String aDataSet) {
        logger.info("--- role list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return roleService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 查询全部角色
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Role> findAll(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return roleService.findAll(user.getStoreId());
    }

    /**
     * 角色添加
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.GET)
    public String addRole(){
        return "role/add";
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addRole(Role role, HttpSession session, HttpServletRequest request){
        AjaxObj json = new AjaxObj();
        String permissions = StringUtil.formatStr(request.getParameter("permissions"));
        User user = (User)session.getAttribute("loginUser");
        role.setStoreId(user.getStoreId());
        roleService.add(role, permissions);
        json.setInfo("角色添加成功");
        json.setStatus("y");
        return json;
    }
}
