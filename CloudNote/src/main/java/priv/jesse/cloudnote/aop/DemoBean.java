package priv.jesse.cloudnote.aop;

import org.springframework.stereotype.Component;

//@Component("demoBean")
public class DemoBean {

	public DemoBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void test (String str){
		System.out.println(str.length());
	}

	public void demo(String str){
		System.out.println("demo:"+str.length());
	}
}
