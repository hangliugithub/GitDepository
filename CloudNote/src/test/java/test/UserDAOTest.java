package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.dao.UserDAO;
import priv.jesse.cloudnote.entity.User;

public class UserDAOTest {

	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");

	}

	@Test //≤‚ ‘MyBatis≈‰÷√
	public void testMapperScanner() {
		Object obj = ac.getBean("mapperScanner");
		System.out.println(obj);
	}

	@Test
	public void testFindUserById() {
		// System.out.println(ac);
		String id = "03590914-a934-4da9-ba4d-b41799f917d1";
		UserDAO dao = ac.getBean("userDAO", UserDAO.class);
		User user = dao.findUserById(id);
		System.out.println(user);

	}

	@Test
	public void testSaveUser() {
	}

}
