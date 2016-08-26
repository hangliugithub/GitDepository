package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import annotation.Restaurant;
import annotation.School;
import annotation.WinBar;
import annotationtest.Manager;

public class TestCase {
	private AbstractApplicationContext ac;
	/*
	 * 添加了@Before的方法，会在测试方法运行之前运行
	 */
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	@Test
	public void test1(){
		Restaurant rt = ac.getBean("rest",Restaurant.class);
		System.out.println(rt);
		ac.close();
	}
	
	@Test
	public void test2(){
		School school = ac.getBean("school",School.class);
		System.out.println(school);
		ac.close();
	}
	
	@Test
	public void test3(){
		WinBar winbar = ac.getBean("wb",WinBar.class);
		System.out.println(winbar);
	}
	
	@Test
	public void test4(){
		Manager mgr = ac.getBean("mgr",Manager.class);
		System.out.println(mgr);
	}
	
}
