package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.SomeBean;

public class TestSomeBean {
	@Test
	public void test1(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("sb.xml");
		SomeBean sb = ac.getBean("sb",SomeBean.class);
		System.out.println(sb);
		ac.close();
	}
}
