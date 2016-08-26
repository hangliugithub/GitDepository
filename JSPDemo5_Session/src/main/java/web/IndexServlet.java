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
		//�ٴ�����ʱ��������ὫsessionId�Զ����͸�tomcat��������
		//tomcat��ͨ��id�ҵ���һ������ʱ�������Ǹ�session����������ɵ�session����request
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
