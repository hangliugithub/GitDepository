package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;

public class UserServiceTest {

private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
	}
	
	@Test
	public void testLogin() {
		UserService service = (UserService) ac.getBean("userService");
		User user = service.login("jackson", "123456456");
		System.out.println(user);
	}

	@Test
	public void testRegist() {
		UserService service = (UserService) ac.getBean("userService");
		service.regist("jackson", "123456456", "jack");
	}

	@Test
	public void testCheckPassword() {
		//UserService service = (UserService) ac.getBean("userService");
		
	}

	@Test
	public void testChangePassword() {
		//UserService service = (UserService) ac.getBean("userService");
	}

}
