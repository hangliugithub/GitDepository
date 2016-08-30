package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpMapper;

public class TestCase {
	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	@Test
	public void test1(){
		//System.out.println(ac);
		EmpMapper mapper = ac.getBean("empMapper", EmpMapper.class);
		System.out.println(mapper.findAll());
	}
}
