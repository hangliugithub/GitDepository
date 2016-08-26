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
		 * bean��Ĭ��idΪ����ĸСд֮�������
		 */
		//ExampleBean eb = ac.getBean("exampleBean",ExampleBean.class);
		ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb);
	}
	
	@Test
	public void test2(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		/*
		 * bean��Ĭ��idΪ����ĸСд֮�������
		 */
		//ExampleBean eb = ac.getBean("exampleBean",ExampleBean.class);
		ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb);
		ac.close();
	}
	
	/**
	 * ����bean��������
	 */
	@Test
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		ExampleBean eb1 = ac.getBean("eb",ExampleBean.class);
		ExampleBean eb2 = ac.getBean("eb",ExampleBean.class);
		System.out.println(eb1==eb2);
	}
	
	/**
	 * �����ӳټ���
	 */
	@Test
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
	}
}
