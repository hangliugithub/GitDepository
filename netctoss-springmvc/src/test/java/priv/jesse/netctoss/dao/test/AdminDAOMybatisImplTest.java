package priv.jesse.netctoss.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.netctoss.dao.AdminDAO;
import priv.jesse.netctoss.entity.Admin;

public class AdminDAOMybatisImplTest {
	
	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	@Test
	public void testFindByAdminCode() {
//		System.out.println(ac);
		AdminDAO dao = (AdminDAO) ac.getBean("adminDAO");
		Admin admin  = dao.findByAdminCode("caocao");
		System.out.println(admin);
	}

}
