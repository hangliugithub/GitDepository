package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * 演示Cookie使用，模拟开发登录功能，在登录时创建Cookie，保存页面存入的账号，在将Cookie发送给浏览器
	 * 浏览器会自动保存Cookie
	 * 此方法相当于netctoss项目中的login()
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接收页面传入的账号
		String code = req.getParameter("code");
		//将账号存入Cookie
		//Cookie中只能保存一对键值对，并且key和value必须都是String
		Cookie c1 = new Cookie("code", code);
		
		//设置cookie的有效路径
		c1.setPath("/JSPDemo4_Cookie");
		
		//设置Cookie的生存时间
		c1.setMaxAge(100);
		
		//将cookie加入到response内，在响应时由服务器自动发送给浏览器
		res.addCookie(c1);
		
		//再创建一个Cookie存中文，默认cookie不能存中文，否则报错。
		//需要对中文转码(ASCII)后才能保存
		Cookie c2 = new Cookie("city",URLEncoder.encode("北京", "utf-8"));
		res.addCookie(c2);
	}

}
