package test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpMapper;
import entity.Emp;
/*
 * 测试spring整合mybatis
 * 使用spring来管理mapper代理对象
 * 
 */
public class EmpMapperTest {
	
	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	@Test
	public void test() {
		EmpMapper mapper = (EmpMapper) ac.getBean("empMapper");
//		System.out.println(mapper);
		List<Emp> list = mapper.findAll();
		System.out.println(list);
	}
	
	@Test
	public void test2(){
		EmpMapper mapper = (EmpMapper) ac.getBean("empMapper");
		System.out.println(mapper.findById(2));
	}
	
	@Test
	public void test3(){
		EmpMapper mapper = (EmpMapper) ac.getBean("empMapper");
		Emp emp = mapper.findById(2);
		emp.setName("Darlin");
		mapper.update(emp);
	}

}
