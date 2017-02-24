package cn.dahe.controller;


import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.User;
import cn.dahe.service.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
@Controller
@RequestMapping("server/permission")
public class PermissionController {
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Resource
    private IPermissionService permissionService;
    //===================================client permission begin================================================
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/clientList", method = RequestMethod.GET)
    public String getClientPermissionList() {
        return "permission/clientList";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/clientList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Permission> getClientPermissionList(HttpSession session, String aDataSet) {
        logger.info("--- permission list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return permissionService.findByParams(aDataSet, user.getStoreId(), 1);
    }

    /**
     * 查询全部权限
     * @return
     */
    @RequestMapping(value = "findAllClientPermission", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> findAllClientPermission(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return permissionService.findAll(user.getStoreId(), 1);
    }
    //===================================client permission end================================================
    //===================================web permission begin================================================
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/webList", method = RequestMethod.GET)
    public String getwebPermissionList() {
        return "permission/webList";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/webList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Permission> getwebPermissionList(HttpSession session, String aDataSet) {
        logger.info("--- permission list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return permissionService.findByParams(aDataSet, user.getStoreId(), 0);
    }

    /**
     * 查询全部权限
     * @return
     */
    @RequestMapping(value = "findAllWebPermission", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> findAllWebPermission(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return permissionService.findAll(user.getStoreId(), 0);
    }
    //===================================web permission end================================================
    /**
     *权限添加
     * @param session
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPermission(HttpSession session){
        return "permission/addPermission";
    }

    /**
     * 权限添加
     * @param permission
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addPermission(Permission permission, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        permission.setStoreId(user.getStoreId());
        permissionService.add(permission);
        json.setMsg("权限添加成功");
        json.setResult(1);
        return json;
    }
}
