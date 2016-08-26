package basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 演示组件扫描
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
		System.out.println("初始化ExampleBean...");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("销毁ExampleBean...");
	}
	
}
