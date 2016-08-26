package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.MessageBean;
import basic.Student;
import ioc.A;

/**
 * 测试类
 * @author Jesse
 *
 */
public class TastCase {
	
	@Test
	/*
	 * 测试生命周期想关的几个方法
	 */
	public void test1(){
		//启动spring容器
		//因为ApplicationContext没有提供关闭容器的方法，所以如果要关闭容器应该使用子接口
		//ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		
		//通过容器获得对象
		MessageBean mb = ac.getBean("mb",MessageBean.class);
		mb.sendMessage();
		
		//关闭容器
		ac.close();
	}
	
	@Test
	/*
	 * 测试作用域
	 */
	public void test2(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		Student s1 = ac.getBean("student",Student.class);
		Student s2 = ac.getBean("student",Student.class);
		System.out.println(s1==s2);//true
 	}
	
	@Test
	/*
	 * 测试延迟加载
	 */
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		
	}
	
	@Test
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2_ioc.xml");
		A a = ac.getBean("a",A.class);
		a.execute();
	}
	
}
