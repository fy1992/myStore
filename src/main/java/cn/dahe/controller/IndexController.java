package cn.dahe.controller;

import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 首页跳转
 * @author fengyuan
 * */
@Controller
public class IndexController {
	/**
	 * @param model
	 * @param session
	 * */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String add(Model model,HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if(null != user){
			model.addAttribute("user", user);
			Set<Permission> permissionSet = user.getPermissions();
			Role role = user.getRole();
			if(role != null){
                Set<Permission> permissions = role.getPermissions();
                permissionSet.addAll(permissions);
            }
            List<Permission> channel = new ArrayList<>();
			List<Permission> menu = new ArrayList<>();
            permissionSet.forEach(permission -> {
                if(permission.getResourceType() == 0){
					channel.add(permission);
                }
                if(permission.getResourceType() == 1){
					menu.add(permission);
                }
            });
            Collections.sort(channel, Comparator.comparing(Permission::getId));
            Collections.sort(menu, Comparator.comparing(Permission::getId));
			model.addAttribute("channel", channel);
			model.addAttribute("menu", menu);
			return "backstage/index";
		}else{
			return "login";
		}
	}
	
	/**
	 * @param model
	 * */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("loginIp", "127.0.0.1");
		model.addAttribute("loginTime", new Date());
		return  "welcome";
	}

	/**
	 * 权限不够的跳转
	 * @return
	 */
	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "403";
	}
}
