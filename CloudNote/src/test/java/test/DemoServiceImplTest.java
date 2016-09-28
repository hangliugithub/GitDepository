package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.DemoService;

public class DemoServiceImplTest {

	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-mybatis.xml");
		System.out.println(ac);
	}
	
	
	@Test
	public void testBatchRegist() {
		DemoService service = (DemoService) ac.getBean("demoService");
		List<User>list= service.batchRegist("zhangshang","Lee","zhoujia","Bluce");
		System.out.println(list);
	}

}
