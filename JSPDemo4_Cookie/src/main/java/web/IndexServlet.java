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
		//获取cookie，该cookie是由浏览器自动传入的是在本次请求之前的请求中创建出来的数据
		Cookie[] cs = req.getCookies();
		//将cookie输出给浏览器
		if(cs!=null){
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			for(Cookie c : cs){
				out.println("<p>");
				//对中文内容的cookie进行解码
				out.println(c.getName()+":"+URLDecoder.decode(c.getValue(),"utf-8"));
				out.println("</p>");
			}
			out.close();
		}
	}

}
