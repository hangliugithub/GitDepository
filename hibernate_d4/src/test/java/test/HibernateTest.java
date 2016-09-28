package test;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import priv.jesse.entity.User;

public class HibernateTest {
	
	private SessionFactory factory;
	
	@Before
	public void init(){
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	
	@Test
	public void testGetUser() {
		Session session = factory.openSession();
		User user = (User) session.get(User.class, "2d883c18-d5a7-4d19-8fce-396592174844");
		System.out.println(user);
	}
	
	@Test
	public void testSave(){
		Session session = factory.openSession();
		String id = UUID.randomUUID().toString();
		User user = new User(id, "hiberante", "123456", "","hi");
		//System.out.println(user);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			System.out.println(session.get(User.class, id));
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	@Test
	public void testUpdate(){
		Session session = factory.openSession();
		String id= "2d883c18-d5a7-4d19-8fce-396592174844";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			user.setNick("catalina");
			//session.update(user);
			tx.commit();
			System.out.println(session.get(User.class, id));
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	@Test
	//abcdefghijklm              
	public void testDelete(){
		Session session = factory.openSession();
		String id= "f4a59629-5a36-47a4-8f35-debf70b97ce6";
		User user = (User) session.get(User.class, id);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			System.out.println(session.get(User.class, id));
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
			
		}
	}

}








