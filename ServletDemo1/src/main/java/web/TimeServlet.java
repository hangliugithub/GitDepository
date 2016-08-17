package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.获取当前服务器时间（Tomcat所运行的计算机）
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(date);
		//2.将时间拼入网页并输出给浏览器
		//向浏览器声明输出的内容的类型
		res.setContentType("text/html");
		//获取输出流并使用它向浏览器输出
		PrintWriter out = res.getWriter();
		//动态拼HTML:正常应该一行一行的拼出HTML，此处就简化拼出body中的内容即可
		out.println("<P>"+now+"</p>");
		//关闭输出流
		out.close();
	}

}





















