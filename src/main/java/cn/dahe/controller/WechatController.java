package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.model.*;
import cn.dahe.service.*;
import cn.dahe.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 微信接口 Created by fy on 2017/3/17.
 */
@Controller
@RequestMapping("wechat")
public class WechatController {
	@Resource
	private IVipService vipService;
	@Resource
	private IUserService userService;
	@Resource
	private IStoreService storeService;
	@Resource
	private IClientOrderService orderService;
	@Resource
	private ICategoriesService categoriesService;
	@Resource
	private IClientGoodsService clientGoodsService;

	/**
	 * 微信 管理员登陆
	 * 
	 * @param openId
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "adminLogin", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj adminLogin(String openId, String username, String password, HttpSession session) {
		User user = userService.findByOpenId(openId);
		AjaxObj json = new AjaxObj();
		if (user == null) {
			user = userService.findByLoginName(username);
			if (user != null) {
				if (SecurityUtil.MD5(password).equals(user.getPassword())) {
					user.setOpenId(openId);
					userService.update(user);
					session.setAttribute("wechatUser", user);
					json.setResult(1);
				} else {
					json.setResult(0);
					json.setMsg("用户名或密码错误");
				}
			} else {
				json.setResult(0);
				json.setMsg("用户名或密码错误");
			}
		} else {
			if (SecurityUtil.MD5(password).equals(user.getPassword())) {
				json.setResult(1);
				session.setAttribute("wechatUser", user);
			} else {
				json.setResult(0);
				json.setMsg("用户名或密码错误");
			}
		}
		return json;
	}

	/**
	 * 查询该门店下的所有商品（菜品）类别
	 */
	@RequestMapping(value = "categoriesList", method = RequestMethod.GET)
	@ResponseBody
	public AjaxObj getGoodsCategoriesList(int storeId, String openId) {
		AjaxObj json = new AjaxObj();
		List<Categories> categoriesList = categoriesService.findAll(storeId);
		json.setResult(1);
		json.setObject(categoriesList);
		return json;
	}

	/**
	 * 查询该门店下的所有商品（菜品）
	 */
	@RequestMapping(value = "goodsList", method = RequestMethod.GET)
	@ResponseBody
	public AjaxObj goodsList(int storeId, int cid, String openId) {
		AjaxObj json = new AjaxObj();
		List<ClientGoods> categoriesList = clientGoodsService.goodsListByCategories(cid, storeId);
		json.setResult(1);
		json.setObject(categoriesList);
		return json;
	}

	/**
	 * 查看所有历史订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "orderList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj orderList(String openId) {
		AjaxObj json = new AjaxObj();
		List<ClientOrder> list = orderService.findByOpenId(openId);
		json.setResult(1);
		json.setObject(list);
		return json;
	}

	/**
	 * 查看订单状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "orderDetail", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj orderDetail(String openId, String orderNo) {
		AjaxObj json = new AjaxObj();
		if (StringUtils.isBlank(openId)) {
			json.setResult(0);
			json.setMsg("服务器错误");
			return json;
		}
		ClientOrder clientOrder = orderService.findByClientOrderNo(orderNo);
		if (clientOrder.getOpenId().equals(openId)) {
			json.setResult(1);
			json.setObject(clientOrder);
		} else {
			json.setResult(0);
			json.setMsg("服务器错误");
		}
		return json;
	}

	/**
	 * 查看会员信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "personal", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj personal(String openId) {
		AjaxObj json = new AjaxObj();
		Vip vip = vipService.findByOpenId(openId);
		if (vip == null) {
			json.setResult(0);
			json.setMsg("请注册");
			return json;
		}
		json.setResult(1);
		json.setObject(vip);
		return json;
	}

	/**
	 * 查看优惠券
	 * 
	 * @return
	 */
	@RequestMapping(value = "coupon", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj coupon(String openId) {
		AjaxObj json = new AjaxObj();
		Vip vip = vipService.findByOpenId(openId);
		if (vip == null) {
			json.setResult(0);
			json.setMsg("请注册");
			return json;
		}
		json.setResult(1);
		json.setObject(vip);
		return json;
	}

	/**
	 * 查看店面信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "storeDetail", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj storeDetail(String openId, int storeId) {
		AjaxObj json = new AjaxObj();
		Store store = storeService.get(storeId);
		json.setResult(1);
		json.setObject(store);
		return json;
	}

}
