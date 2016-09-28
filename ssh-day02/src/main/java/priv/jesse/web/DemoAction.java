package priv.jesse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")//设置为多例的
public class DemoAction {

	@Autowired
	private DemoService demoService;
	
	private String message;
	
	public String getMessage(){
		return message;
	}
	
	public DemoAction() {
		// TODO Auto-generated constructor stub
	}

	
	public String execute(){
		//从业务层传送数据到jsp
		message = demoService.test();
		
		return "success";
	}
}
