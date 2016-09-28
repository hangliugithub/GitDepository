package priv.jesse.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {

	public DemoListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * web容器环境（上下文）销毁时候执行
	 */
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * web容器环境（上下文）初始化时候执行
	 */
	public void contextInitialized(ServletContextEvent e) {
		//获取ServletContext的名字
		ServletContext ctx = e.getServletContext();
		String path = ctx.getContextPath();
		String name = ctx.getServletContextName();
		String info = ctx.getServerInfo();
		System.out.println("path:"+path);
		System.out.println("name:"+name);
		System.out.println("info:"+info);
		System.out.println("初始化完成");
		
	}

}
