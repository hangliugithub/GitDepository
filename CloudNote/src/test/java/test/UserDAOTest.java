package test;

import java.util.UUID;

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

	@Test // 测试MyBatis配置
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

	/**
	 * 测试userDAO的save方法
	 */
	@Test
	public void testSaveUser() {
		UserDAO dao = (UserDAO) ac.getBean("userDAO");
		// System.out.println(dao);
		User user = new User();
		//利用java.util.UUID类的方法生成唯一id
		user.setId(UUID.randomUUID().toString());
		user.setName("Catalina");
		user.setNick("Cat");
		user.setPassword("123456");
		
		dao.saveUser(user);
	}
	
	@Test
	public void testFindUserByName() {
		// System.out.println(ac);
		String name = "Catalina";
		UserDAO dao = ac.getBean("userDAO", UserDAO.class);
		User user = dao.findUserByName(name);
		System.out.println(user);

	}
	
//	@Test
//	public void test(){
//		out:
//		for(int i=0;i<10;i++){
//			for(int j=0;j<32;j++){
//				if(j>10){
//					System.out.println(j);
//					break out;
//				}
//			}
//			
//		}
//	}
}
