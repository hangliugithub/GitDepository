package basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * ��ʾ���ɨ��
 * @author Jesse
 *
 */
@Component("eb")
//@Scope("prototype")
@Lazy(true)
public class ExampleBean {
	
	public ExampleBean() {
		System.out.println("ExampleBean's ExampleBean()");
	}
	@PostConstruct
	public void init(){
		System.out.println("��ʼ��ExampleBean...");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("����ExampleBean...");
	}
	
}
