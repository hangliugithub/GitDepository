package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//每个浏览器在第一次访问此项目时，tomcat都会给它创建一个session，
		//然后将session(引用)存入request
		HttpSession session = req.getSession();
		//session可以存任意类型的数据
		String code = req.getParameter("code");
		session.setAttribute("code", code);
	}

}
