package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//再次请求时，浏览器会将sessionId自动发送给tomcat服务器，
		//tomcat会通过id找到第一次请求时创建的那个session，并将这个旧的session存入request
		HttpSession session = req.getSession();
		String code = (String) session.getAttribute("code");
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<p>");
		out.println("code"+":"+code);
		out.println("</p>");
		out.close();
	}

}
