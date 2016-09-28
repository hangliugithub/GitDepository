package priv.jesse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DemoController {

	public DemoController() {
		System.out.println(Thread.currentThread().getName()+"创建对象DemoController对象");
	}

	int num = 5;
	@RequestMapping("/demo")
	public void login(String name){
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":name:"+name+",num:"+num);
		
	}
	
}
