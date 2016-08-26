package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.netctoss.dao.EmpDAO;
import priv.jesse.netctoss.entity.Emp;

/**
 * ≤‚ ‘springjdbc
 * @author Jesse
 *
 */
public class TestEmpDAO {
	private EmpDAO dao;
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("appcontext.xml");
		dao = ac.getBean("empDAO",EmpDAO.class);
	}
	@Test
	public void test1(){
		Emp emp = new Emp();
		emp.setName("Catalina");
		emp.setAge(22);
		dao.save(emp);
	}
	
	@Test
	public void test2(){
		List<Emp> list = dao.findAll();
		System.out.println(list);
	}
	
	@Test
	public void test3(){
		System.out.println(dao.findById(105));
	}
	
	@Test
	public void test4(){
		Emp emp = dao.findById(101);
		emp.setAge(19);
		dao.updata(emp);
		System.out.println(dao.findById(101));
	}
	
	@Test
	public void test5(){
		dao.delete(101);
	}
}
