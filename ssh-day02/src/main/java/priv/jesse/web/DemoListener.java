package priv.jesse.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {

	public DemoListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * web���������������ģ�����ʱ��ִ��
	 */
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * web���������������ģ���ʼ��ʱ��ִ��
	 */
	public void contextInitialized(ServletContextEvent e) {
		//��ȡServletContext������
		ServletContext ctx = e.getServletContext();
		String path = ctx.getContextPath();
		String name = ctx.getServletContextName();
		String info = ctx.getServerInfo();
		System.out.println("path:"+path);
		System.out.println("name:"+name);
		System.out.println("info:"+info);
		System.out.println("��ʼ�����");
		
	}

}
