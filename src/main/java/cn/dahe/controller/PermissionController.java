package cn.dahe.controller;


import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Permission;
import cn.dahe.model.User;
import cn.dahe.service.IPermissionService;
import cn.dahe.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fy on 2017/2/9.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Resource
    private IPermissionService permissionService;

    /**
     * 查询全部权限
     * @return
     */
    @RequestMapping(value = "findAllPermission", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> findAllPermission(int type, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return permissionService.findAll(user.getStoreId(), type);
    }
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getPermissionList() {
        return "permission/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Permission> getPermissionList(HttpSession session, String aDataSet) {
        logger.info("--- permission list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return permissionService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     *权限添加
     * @param session
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPermission(HttpSession session){
        return "permission/add";
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
        json.setInfo("权限添加成功");
        json.setStatus("y");
        return json;
    }

    /**
     *权限修改
     * @param session
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editPermission(HttpSession session){
        return "permission/edit";
    }

    /**
     * 权限修改
     * @param permission
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editPermission(Permission permission, HttpSession session, HttpServletRequest request){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        permission.setStoreId(user.getStoreId());
        int parentId = StringUtil.formatStr2Int(request.getParameter("parentId"));
        permissionService.update(permission, parentId);
        json.setInfo("权限添加成功");
        json.setStatus("y");
        return json;
    }
}
