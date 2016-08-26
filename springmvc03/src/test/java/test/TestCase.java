package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.netctoss.dao.AdminDAO;
import priv.jesse.netctoss.entity.Admin;
import priv.jesse.netctoss.service.LoginService;

public class TestCase {
	private ApplicationContext ac;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("appcontext.xml");
	}
	
	//�����ܷ��ȡ���ݿ����������ļ�
	@Test
	public void test1(){
		System.out.println(ac.getBean("config"));
	}
	
	//����DataSource����Դ
	@Test
	public void test2() throws SQLException{
		DataSource ds = ac.getBean("datasource",DataSource.class);
		System.out.println(ds.getConnection());
	}
	
	//����dao
	@Test
	public void test3(){
		AdminDAO dao = ac.getBean("adminDAO",AdminDAO.class);
		System.out.println(dao.findByAdminCode("caocao"));
	}
	
	//����loginService
	@Test
	public void test4(){
		LoginService service = ac.getBean("loginService",LoginService.class);
		Admin admin = service.checkLogin("caocao", "123");
		System.out.println(admin);
		
	}
	
}







