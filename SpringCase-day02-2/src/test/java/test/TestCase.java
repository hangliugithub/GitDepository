package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.ExampleBean;

public class TestCase {
	@Test
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		/*
		 * bean的默认id为首字母小写之后的类名
		 */
		//ExampleBean eb = ac.getBean("exampleBean",ExampleBean.class);
		ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb);
	}
	
	@Test
	public void test2(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		/*
		 * bean的默认id为首字母小写之后的类名
		 */
		//ExampleBean eb = ac.getBean("exampleBean",ExampleBean.class);
		ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb);
		ac.close();
	}
	
	/**
	 * 测试bean的作用域
	 */
	@Test
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		ExampleBean eb1 = ac.getBean("eb",ExampleBean.class);
		ExampleBean eb2 = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb1==eb2);
	}
	
	/**
	 * 测试延迟加载
	 */
	@Test
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
	}
}
