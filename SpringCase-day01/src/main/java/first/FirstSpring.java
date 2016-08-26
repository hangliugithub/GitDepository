package first;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.ExampleBean;

/**
 * 演示如何启动Spring容器
 * 
 * @author Jesse
 *
 */
public class FirstSpring {
	public static void main(String[] args) {
		// 注意配置文件的路径
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// System.out.println(ac);

		/*
		 * 1.使用默认无参构造创建的实例
		 */
		// ExampleBean eb = (ExampleBean)ac.getBean("eb");
		// ExampleBean eb = ac.getBean("eb",ExampleBean.class);
		// System.out.println(eb);

		// 创建一个java.util.Date对象
		Date date = ac.getBean("date1", Date.class);
		System.out.println(date);

		/*
		 * 2.使用静态工厂方法创建的实例
		 */
		Calendar cal = ac.getBean("cal", Calendar.class);
		System.out.println(cal);
		
		/*
		 * 3.使用实例工厂方法创建实例
		 */
		Date date2 = ac.getBean("time1",Date.class);
		System.out.println(date2);
	}
}
