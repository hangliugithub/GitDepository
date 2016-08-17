package web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查拦截器，在访问netctoss各个功能之前检查用户是否登录
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//tomcat调用此方法时传入的参数是声明参数的子类型HttpServletRequest和HttpServletResponse
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//有三个请求不需要判断，排除他们
		String[] paths = new String[]{"/toLogin.do","/login.do","/createImg.do"};
		String path = request.getServletPath();
		for(String s : paths){
			if(s.equals(path)){
				//当前路径不需判断，则继续执行请求
				chain.doFilter(request, response);
				return;
			}
		}
		
		//判断用户是否登录
		//若session中存储了adminCode,则已经登录，否则未登录
		HttpSession session = request.getSession();
		String adminCode = (String)session.getAttribute("adminCode");
		if(adminCode==null){
			//未登录重定向到登录页面
			response.sendRedirect("/NetCTOSS/toLogin.do");
		}else{
			//已登录，请求继续执行
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
