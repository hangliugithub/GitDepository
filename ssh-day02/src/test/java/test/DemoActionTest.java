package test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.web.DemoAction;

public class DemoActionTest {
	
	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-web.xml");
	}
	
	@Test
	public void testExecute() {
		DemoAction action = (DemoAction) ac.getBean("demoAction");
		String str = action.execute();
		System.out.println(action.getMessage());
		System.out.println(str);
	}

}
