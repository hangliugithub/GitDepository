package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Computer;

public class TestComputer {
	@Test
	public void test1(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("di.xml");
		Computer c1 = ac.getBean("computer", Computer.class);
		System.out.println(c1);
		System.out.println(c1.getCpu());
		ac.close();
		
	}
}
