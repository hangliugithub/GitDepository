package web;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListener implements ServletRequestListener,ServletRequestAttributeListener{

	public void requestDestroyed(ServletRequestEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����request");

	}

	public void requestInitialized(ServletRequestEvent e) {
		// TODO Auto-generated method stub
		System.out.println("��ʼ��request");
		System.out.println(e.getServletRequest());
	}

	public void attributeAdded(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		System.out.println("��request���������");
		System.out.println(e.getName()+":"+e.getValue());
	}

	public void attributeRemoved(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(ServletRequestAttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
