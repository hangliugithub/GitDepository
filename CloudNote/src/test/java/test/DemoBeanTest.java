package test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jess.cloudnote.aop.DemoBean;

public class DemoBeanTest {

	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-aop.xml");
		System.out.println(ac);
	}
	
	@Test
	public void test() {
		DemoBean bean  = (DemoBean)ac.getBean("demoBean");
		bean.test(null);
	}
	
	@Test
	public void testAround(){
		DemoBean bean = (DemoBean) ac.getBean("demoBean");
		bean.demo("catalina");
		bean.demo(null);
	}

}
