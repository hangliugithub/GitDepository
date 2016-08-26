package priv.jesse.netctoss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object admin = session.getAttribute("admin");
		if(admin == null){
			//没有登录，重定向到登录页面
			response.sendRedirect("toLogin.do");
			return false;
		}
		return true;
	}
}
