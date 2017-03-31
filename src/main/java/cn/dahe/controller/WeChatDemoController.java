package cn.dahe.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dahe.model.Categories;
import cn.dahe.model.ClientGoods;
import cn.dahe.service.ICategoriesService;
import cn.dahe.service.IClientGoodsService;
import cn.dahe.util.HttpRequestProxy;
import cn.dahe.util.WechatConstant;
import weixin.popular.api.SnsAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.token.Token;
import weixin.popular.bean.xmlmessage.XMLMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

/**
 * 微信demo controller
 * 
 * @author ALERT
 *
 */
@Controller
@RequestMapping("wechatdemo")
public class WeChatDemoController {

	private static Logger logger = LoggerFactory.getLogger(WeChatDemoController.class);

	@Resource
	private IClientGoodsService clientGoodsService;
	@Resource
	private ICategoriesService categoriesService;

	/**
	 * 微信服务器验证
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("wx")
	public void wxValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String timestamp = request.getParameter("timestamp");
		String signature = request.getParameter("signature");
		// 首次请求申请验证,返回echostr
		if (echostr != null) {
			response.getWriter().write(echostr);
			return;
		}
		// 验证请求签名
		if (!signature.equals(SignatureUtil.generateEventMessageSignature(WechatConstant.token, timestamp, nonce))) {
			System.out.println("The request signature is invalid");
			return;
		}
		if (inputStream != null) {
			// 转换XML
			EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
			// 如果是订阅，则向用户发送欢迎消息
			if ("subscribe".equals(eventMessage.getEvent())) {
				// 创建回复
				XMLMessage xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
						eventMessage.getToUserName(), "您好，欢迎关注！");
				xmlTextMessage.outputStreamWrite(response.getOutputStream());
				return;
			}
		}
		response.getWriter().write("");
	}

	/**
	 * 微信登陆认证
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "wxAuth")
	public String wxAuth(String return_url, String code, HttpSession session) {
		logger.info("Return_url:" + return_url);
		SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConstant.appid, WechatConstant.secret, code);
		// 根据openId查找用户,如果有则放入session中,没有则新建vip用户。
		if (snsToken != null && StringUtils.isNotBlank(snsToken.getOpenid())) {
			logger.info("OpenId:" + snsToken.getOpenid());
			session.setAttribute("wxUser", snsToken.getOpenid());
		}
		return "redirect:" + return_url;
	}

	/**
	 * 商品列表
	 * 
	 * @param return_url
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "goodsList")
	public String wxChooseGoods(Model model) {
		List<Categories> categList = categoriesService.findAll(1);
		model.addAttribute("categList", categList);
		return "wechat/public_choose";
	}

	/**
	 * 根据类别id查找下属商品
	 * @param categId
	 * @return
	 */
	@RequestMapping(value = "goodsByCategId")
	@ResponseBody
	public List<ClientGoods> goodsByCategId(int categId){
		if(categId == 0){
			return clientGoodsService.findAll(1);
		}else{
			return clientGoodsService.goodsListByCategories(categId, 1);
		}
	}

	/**
	 * 自定义创建菜单
	 */
	public static void creteMenu() {
		Token token = TokenAPI.token("wxc7e0539a9fe20a3d", "d4624c36b6795d1d99dcf0547af5443d");
		String callbackUrl = "http://694059031.tunnel.2bdata.com/store/wechatdemo/goodsList";
		String entity = "{\"button\":[{\"type\":\"view\",\"name\":\"壹号掌柜\",\"url\":\"" + callbackUrl + "\"}]}";
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token.getAccess_token();
		System.out.println(HttpRequestProxy.doPost(url, entity));
	}

	public static void main(String[] args) {
		// getAccess_token();
		creteMenu();
	}

}
