package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//��ȡcookie����cookie����������Զ���������ڱ�������֮ǰ�������д�������������
		Cookie[] cs = req.getCookies();
		//��cookie����������
		if(cs!=null){
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			for(Cookie c : cs){
				out.println("<p>");
				//���������ݵ�cookie���н���
				out.println(c.getName()+":"+URLDecoder.decode(c.getValue(),"utf-8"));
				out.println("</p>");
			}
			out.close();
		}
	}

}
