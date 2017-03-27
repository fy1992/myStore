package cn.dahe.interceptor;

import cn.dahe.util.WechatConstant;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

public class WechatInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WechatInterceptor.class);

	@Override
	@SuppressWarnings("deprecation")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (!checkAllowAccess(url)) {
			HttpSession session = request.getSession();
			String openId = (String) session.getAttribute("wxUser");
			// 如果session中不存在此用户，则重定向到微信网页授权链接
			if (StringUtils.isBlank(openId)) {
				logger.info("微信端用戶认证失效:" + url);
				StringBuilder sbAuthUrl = new StringBuilder();
				String callbackUrl = "http://694059031.tunnel.2bdata.com/store/wechatdemo/wxAuth?return_url=" + url;
				sbAuthUrl.append("https://open.weixin.qq.com/connect/oauth2/authorize");
				sbAuthUrl.append("?redirect_uri=" + URLEncoder.encode(callbackUrl));
				sbAuthUrl.append("&appid=" + WechatConstant.appid);
				sbAuthUrl.append("&response_type=code");
				sbAuthUrl.append("&scope=snsapi_base");
				sbAuthUrl.append("#wechat_redirect");
				response.sendRedirect(sbAuthUrl.toString());
			}
		}
		return true;
	}

	/**
	 * 检查当前请求是否放行
	 * 
	 * @param url
	 * @return
	 */
	private boolean checkAllowAccess(String url) {
		if (!url.startsWith("/")) {
			url = "/" + url;
		}
		if (url.equals("/wechatdemo/wx") || url.equals("/wechatdemo/wxAuth")) {
			return true;
		}
		return false;
	}

}
