package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMysql {
	private DataSource dataSource;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("appcontext.xml");
		dataSource = ac.getBean("datasource",DataSource.class);
	}
	
	@Test
	public void test1() throws SQLException{
		System.out.println(dataSource.getConnection());
	}
	
}
