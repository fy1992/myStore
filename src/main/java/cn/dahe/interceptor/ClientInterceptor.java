package cn.dahe.interceptor;

import cn.dahe.model.Cashier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClientInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		HttpSession session = request.getSession();
		Cashier cashier = (Cashier) session.getAttribute("clientUser");
		if(cashier == null){
			logger.info("客戶端用戶沒有登錄");
		}
		return cashier != null;
		/*if(user == null){
			redictLogin(request, response);
		}
		return true;*/
	}
}
