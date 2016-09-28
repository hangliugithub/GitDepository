package priv.jesse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")//����Ϊ������
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
		//��ҵ��㴫�����ݵ�jsp
		message = demoService.test();
		
		return "success";
	}
}
