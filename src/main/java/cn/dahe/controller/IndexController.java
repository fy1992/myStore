package cn.dahe.controller;

import cn.dahe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
