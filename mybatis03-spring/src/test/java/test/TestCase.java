package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpDAO;

public class TestCase {
	private EmpDAO dao;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("appcontext.xml");
		dao = ac.getBean("empDAO",EmpDAO.class);
	}
	
	@Test
	public void test1(){
		System.out.println(dao.findAll());
	}
}
