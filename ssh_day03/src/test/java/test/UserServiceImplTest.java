package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;

public class UserServiceImplTest {

	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-mybatis.xml","spring-web.xml");
		System.out.println(ac);
	}
	
	@Test
	public void testLogin() {
		UserService service = (UserService) ac.getBean("userService");
		User user = service.login("zhoujia", "123");
		System.out.println(user);
		//System.out.println(ac.getBean("loginAction",LoginAction.class).getUserService());
	}

}
