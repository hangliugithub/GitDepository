package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans2.Student;

public class TestCase2 {
	@Test
	public void test1(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("appcontext2.xml");
		Student s = ac.getBean("stu", Student.class);
		System.out.println(s);
		ac.close();
	}
}
