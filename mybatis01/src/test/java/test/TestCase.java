package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import dao.EmpDAO;
import dao.EmpMapper;
import entity.Emp;
import utils.MybatisUtil;

public class TestCase {
	private EmpDAO dao;
	
	@Before
	public void init(){
		dao = new EmpDAO();
	}
	
	@Test
	public void test1(){
		List<Emp> list = dao.findAll();
		System.out.println(list);
	}
	
	@Test
	public void test2(){
		System.out.println(dao.findById(2));
	}
	
	@Test
	public void test3(){
		Emp emp = new Emp();
		emp.setName("Skywalker");
		emp.setAge(18);
		dao.save(emp);
		test1();
	}
	
	@Test
	public void test4(){
		Emp emp = dao.findById(6);
		//System.out.println(emp);
		emp.setAge(22);
		dao.update(emp);
		System.out.println(dao.findById(6));
	}
	
	@Test
	public void test5(){
		dao.delete(5);
	}
	
	@Test
	public void test6(){
		List<Map<String,Object>> list = dao.findById2(2);
		Map<String,Object> map = list.get(0);
		System.out.println(map);
	}
	
	/*
	 * ≤‚ ‘Mapper”≥…‰∆˜
	 */
	@Test
	public void test7(){
		//Õ®π˝SqlSessionªÒµ√Mapper”≥…‰∆˜
		EmpMapper mapper = MybatisUtil.getSqlSession().getMapper(EmpMapper.class);
		System.out.println(mapper.findAll());
		System.out.println(mapper.findById(2));
		System.out.println(mapper.findById2(2));
	}
	
	@Test
	public void test8(){
		SqlSession session = MybatisUtil.getSqlSession();
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		mapper.save(new Emp("Jesse",22));
		session.commit();
	}
}















