package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpDAO;
import entity.Emp;

public class TestCase {
	private EmpDAO dao;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("appcontext.xml");
		dao = ac.getBean("empDAO",EmpDAO.class);
	}
	
	@Test
	public void test1(){
		System.out.println(dao.findAll());
		System.out.println(dao.findById(2));
	}
	
	@Test
	public void test2(){
		Emp emp = dao.findById(2);
		emp.setAge(25);
		dao.update(emp);
	}
	
	@Test
	public void test3(){
		dao.delete(8);
	}
	
}
