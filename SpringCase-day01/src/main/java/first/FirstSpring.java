package first;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.ExampleBean;

/**
 * ��ʾ�������Spring����
 * 
 * @author Jesse
 *
 */
public class FirstSpring {
	public static void main(String[] args) {
		// ע�������ļ���·��
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// System.out.println(ac);

		/*
		 * 1.ʹ��Ĭ���޲ι��촴����ʵ��
		 */
		// ExampleBean eb = (ExampleBean)ac.getBean("eb");
		// ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		// System.out.println(eb);

		// ����һ��java.util.Date����
		Date date = ac.getBean("date1", Date.class);
		System.out.println(date);

		/*
		 * 2.ʹ�þ�̬��������������ʵ��
		 */
		Calendar cal = ac.getBean("cal", Calendar.class);
		System.out.println(cal);
		
		/*
		 * 3.ʹ��ʵ��������������ʵ��
		 */
		Date date2 = ac.getBean("time1",Date.class);
		System.out.println(date2);
	}
}
