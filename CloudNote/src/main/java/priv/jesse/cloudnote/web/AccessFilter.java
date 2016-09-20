package priv.jesse.cloudnote.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.jesse.cloudnote.util.Md5;

public class AccessFilter implements Filter {

	public AccessFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//除了拦截login.html 其他html都拦截
		StringBuffer url = request.getRequestURL();
		//System.out.println(url);
		String path = url.toString();
		//拦截*.html和*.do
		if(path.endsWith(".html")||path.endsWith(".do")){
			//放过login.html和/account/*的请求
			if(path.endsWith("login.html") || path.indexOf("/account/")>0){
				chain.doFilter(request, response);
				return;
			}
			
			//如果未登录就转到登录页面
			processAccessControl(request,response,chain);
			return;
		}
		//忽略其他请求
		chain.doFilter(request, response);
		
		
	}

	/**
	 * 处理访问控制
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processAccessControl(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		//检查Cookie中的token，没有就去登录页面
		Cookie[] cookies = request.getCookies();
		Cookie token = null;
		if(cookies==null || cookies.length<=0){
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
			return;
		}
		for(Cookie cookie : cookies){
			if("token".equals(cookie.getName())){
				token = cookie;
				//System.out.println(token);
				break;
			}
		}
		if(token==null){
			//如果未找到，就重定向到登录页面
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
			return;
		}else{
			//检查token是否合格
			String value = token.getValue();
			
			//System.out.println(value);
			String[] data = value.split("\\|");
			if(data.length!=2){
				String path = request.getContextPath();
				String login = path+"/login.html";
				response.sendRedirect(login);
				return;
			}
			String time = data[0];
			String md5 = data[1];
			String userAgent = request.getHeader("User-Agent");
			if(md5.equals(Md5.saltMd5(userAgent+time))){
				chain.doFilter(request, response);
				return;
			}
			//token不符合规则重定向到登录页
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
		}
		
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
