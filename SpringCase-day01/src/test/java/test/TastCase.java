package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.MessageBean;
import basic.Student;
import ioc.A;

/**
 * ������
 * @author Jesse
 *
 */
public class TastCase {
	
	@Test
	/*
	 * ��������������صļ�������
	 */
	public void test1(){
		//����spring����
		//��ΪApplicationContextû���ṩ�ر������ķ������������Ҫ�ر�����Ӧ��ʹ���ӽӿ�
		//ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		
		//ͨ��������ö���
		MessageBean mb = ac.getBean("mb",MessageBean.class);
		mb.sendMessage();
		
		//�ر�����
		ac.close();
	}
	
	@Test
	/*
	 * ����������
	 */
	public void test2(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		Student s1 = ac.getBean("student",Student.class);
		Student s2 = ac.getBean("student",Student.class);
		System.out.println(s1==s2);//true
 	}
	
	@Test
	/*
	 * �����ӳټ���
	 */
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac.xml");
		
	}
	
	@Test
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ac2_ioc.xml");
		A a = ac.getBean("a",A.class);
		a.execute();
	}
	
}
