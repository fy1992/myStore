package cn.dahe.interceptor;

import cn.dahe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ClientInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("clientUser");
		return user != null;
		/*if(user == null){
			redictLogin(request, response);
		}
		return true;*/
	}



    //登录页面跳出iframe
   /* private boolean redictLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        out.write("<script>window.parent.location.href='" + request.getContextPath() + "/login'</script>");
        logger.info(request.getContextPath() + "/login");
        return false;
    }*/
}
