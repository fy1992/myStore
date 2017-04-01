package cn.dahe.interceptor;

import cn.dahe.dto.AjaxObj;
import cn.dahe.model.Cashier;
import cn.dahe.util.CacheUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ClientInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String token = getTokenFromRequest(request);
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        HttpSession session = request.getSession();
        if(!checkAllowAccess(url)){
            if(StringUtils.isNotBlank(token) && CacheUtils.getCashierUser(token) != null) {
                int storeId = Integer.parseInt(token.substring(token.length() - 1));
                Cashier cashier = (Cashier) session.getAttribute("clientUser_" + storeId);
                if (cashier == null) {
                    cashier = (Cashier)CacheUtils.getCashierUser(token);
                    if(cashier.getStoreId() == storeId){
                        logger.info("session过期，重新赋值");
                        session.setAttribute("clientUser_" + storeId, cashier);
                        return true;
                    }else{
                        writeJson(response);
                        return false;
                    }
                }
            }else{
                writeJson(response);
                return false;
            }
        }
		return true;
	}

    /**
     * 检查当前请求是否放行
     * @param url
     * @return
     */
    private boolean checkAllowAccess(String url){
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        if(url.equals("/client/login")){
            return true;
        }
        if(url.equals("/client/storeLogin")){
            return true;
        }
        return false;
    }

	/**
	 * 从请求信息中获取token值
	 * @param request
	 * @return token值
	 */
	private String getTokenFromRequest(HttpServletRequest request) {
		// 默认从header里获取token值
		String token = request.getHeader("app_token_id");
		if (StringUtils.isEmpty(token)) {
			// 从请求信息中获取token值
			token = request.getParameter("app_token_id");
		}
		return token;
	}

	private void writeJson(HttpServletResponse response){
	    try {
            logger.info("客户端没有登录");
            AjaxObj json = new AjaxObj();
            json.setResult(2);
            json.setMsg("账号过期，请重新登录");
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            response.getWriter().write(JSON.toJSONString(json));
            response.getWriter().close();
        }catch (IOException e){
	        e.printStackTrace();
        }
    }
}
