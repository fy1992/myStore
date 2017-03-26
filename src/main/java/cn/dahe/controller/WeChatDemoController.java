package cn.dahe.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dahe.model.Vip;
import cn.dahe.util.HttpRequestProxy;
import cn.dahe.util.WechatConstant;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.token.Token;
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
			// 订阅
			if(eventMessage.getEvent().equals("subscribe")){
				String openId = eventMessage.getFromUserName();
				System.out.println("openId:" + openId);
				System.out.println("event:" + eventMessage.getEvent());
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
		session.setAttribute("wxUser", new Vip());
		logger.info("return_url:" + return_url);
		logger.info("code:" + code);
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
	public String wxAuth() {
		return "wechat/public_choose";
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
