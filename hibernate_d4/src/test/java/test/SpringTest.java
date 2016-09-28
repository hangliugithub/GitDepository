package test;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
	}
	
	@Test
	public void test() {
		SessionFactory factory = ac.getBean("sessionFactory",SessionFactory.class);
		System.out.println(factory.openSession());
	}

}
