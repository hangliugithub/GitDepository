package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.dao.UserDao;

public class UserDaoImplTest {

	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
	}

	@Test
	public void testFindUserById() {
		UserDao dao = ac.getBean(UserDao.class);
		//System.out.println(dao);
		String id= "2d883c18-d5a7-4d19-8fce-396592174844";
		System.out.println(dao.findUserById(id));
	}

}
