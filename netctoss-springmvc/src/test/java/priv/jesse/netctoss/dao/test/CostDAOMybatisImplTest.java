package priv.jesse.netctoss.dao.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.netctoss.dao.CostDAO;

public class CostDAOMybatisImplTest {

	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	@Test
	public void testFindAll() {
		CostDAO dao = (CostDAO) ac.getBean("costDAO");
		System.out.println(dao.findAll());
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

}
