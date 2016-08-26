package web;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListener implements ServletRequestListener,ServletRequestAttributeListener{

	public void requestDestroyed(ServletRequestEvent e) {
		// TODO Auto-generated method stub
		System.out.println("销毁request");

	}

	public void requestInitialized(ServletRequestEvent e) {
		// TODO Auto-generated method stub
		System.out.println("初始化request");
		System.out.println(e.getServletRequest());
	}

	public void attributeAdded(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		System.out.println("向request中添加数据");
		System.out.println(e.getName()+":"+e.getValue());
	}

	public void attributeRemoved(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
