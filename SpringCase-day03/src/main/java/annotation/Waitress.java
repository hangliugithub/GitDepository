package annotation;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * ��ʾʹ��ע��ķ�ʽע��������ϵ
 * @author Jesse
 *
 */
@Component("wt")
public class Waitress implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//private String name;
	
	
	public Waitress() {
		System.out.println("Waitress's Waitress()");
	}
	
}
