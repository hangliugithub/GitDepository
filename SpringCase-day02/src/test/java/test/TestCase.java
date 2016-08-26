package test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ioc.A;
import ioc.Restaurant;
import value.ExampleBean;
import value.SomeBean;

public class TestCase {
	
	/**
	 * 测试构造器注入
	 */
	@Test
	public void test1(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		A a = ac.getBean("a1",A.class);
		a.execute();
		ac.close();
	}
	
	@Test
	public void test2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		Restaurant rs = ac.getBean("restaurant",Restaurant.class);
		System.out.println(rs);
	}
	
	@Test
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2.xml");
		ExampleBean bean = ac.getBean("eb",ExampleBean.class);
		System.out.println(bean);
	}
	
	@Test
	public void rest4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2.xml");
		ExampleBean bean = ac.getBean("eb2",ExampleBean.class);
		System.out.println(bean);
		System.out.println(ac.getBean("dbBean"));
	}
	
	@Test
	public void test5(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2.xml");
		System.out.println(ac.getBean("conf"));
	}
	
	/**
	 * 测试spring表达式
	 */
	@Test
	public void test6(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2.xml");
		SomeBean sb = ac.getBean("sb1",SomeBean.class);
		System.out.println(sb);
	}
}
