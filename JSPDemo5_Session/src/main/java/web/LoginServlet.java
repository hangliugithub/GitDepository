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
		//ÿ��������ڵ�һ�η��ʴ���Ŀʱ��tomcat�����������һ��session��
		//Ȼ��session(����)����request
		HttpSession session = req.getSession();
		//session���Դ��������͵�����
		String code = req.getParameter("code");
		session.setAttribute("code", code);
	}

}
