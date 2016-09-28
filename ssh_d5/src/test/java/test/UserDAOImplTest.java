package test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.dao.UserDAO;
import priv.jesse.cloudnote.entity.User;

public class UserDAOImplTest {

	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
	}
	
	@Test
	public void testFindUserById() {
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		User user = dao.findUserById("1234564798");
		System.out.println(user);
	}

	@Test
	public void testSaveUser() {
		User user = new User("1234564798", "Catali520", "123456", null, "kate");
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		dao.saveUser(user);
	}

	@Test
	public void testFindUserByName() {
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		User user = dao.findUserByName("zhoujia");
		System.out.println(user);
	}
	
	
	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

}
