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
 * ��¼������������ڷ���netctoss��������֮ǰ����û��Ƿ��¼
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
	//tomcat���ô˷���ʱ����Ĳ���������������������HttpServletRequest��HttpServletResponse
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//������������Ҫ�жϣ��ų�����
		String[] paths = new String[]{"/toLogin.do","/login.do","/createImg.do"};
		String path = request.getServletPath();
		for(String s : paths){
			if(s.equals(path)){
				//��ǰ·�������жϣ������ִ������
				chain.doFilter(request, response);
				return;
			}
		}
		
		//�ж��û��Ƿ��¼
		//��session�д洢��adminCode,���Ѿ���¼������δ��¼
		HttpSession session = request.getSession();
		String adminCode = (String)session.getAttribute("adminCode");
		if(adminCode==null){
			//δ��¼�ض��򵽵�¼ҳ��
			response.sendRedirect("/NetCTOSS/toLogin.do");
		}else{
			//�ѵ�¼���������ִ��
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
