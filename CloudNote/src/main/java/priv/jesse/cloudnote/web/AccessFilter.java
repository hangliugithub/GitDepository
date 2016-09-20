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
		//��������login.html ����html������
		StringBuffer url = request.getRequestURL();
		//System.out.println(url);
		String path = url.toString();
		//����*.html��*.do
		if(path.endsWith(".html")||path.endsWith(".do")){
			//�Ź�login.html��/account/*������
			if(path.endsWith("login.html") || path.indexOf("/account/")>0){
				chain.doFilter(request, response);
				return;
			}
			
			//���δ��¼��ת����¼ҳ��
			processAccessControl(request,response,chain);
			return;
		}
		//������������
		chain.doFilter(request, response);
		
		
	}

	/**
	 * ������ʿ���
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processAccessControl(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		//���Cookie�е�token��û�о�ȥ��¼ҳ��
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
			//���δ�ҵ������ض��򵽵�¼ҳ��
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
			return;
		}else{
			//���token�Ƿ�ϸ�
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
			//token�����Ϲ����ض��򵽵�¼ҳ
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
		}
		
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
