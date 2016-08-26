package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans1.Computer;
import beans1.Student;

public class TestCase {
	
	/**
	 * ��ϰͨ��xml�����ļ��ķ�ʽע��bean����
	 */
	@Test
	public void test1(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("appcontext.xml");
		Student s = ac.getBean("student",Student.class);
		Computer c = ac.getBean("computer",Computer.class);
		System.out.println(c);
		System.out.println(s);
		System.out.println(s.getPc());
		ac.close();
	}
	
	
}
