package priv.jesse.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class JsonAction {

	private String message;
	private int age;
	private int[] index;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}

	public JsonAction() {
	}
	
	public String execute(){
		message = "ÄãºÃ£¡";
		age = 20;
		index = new int[]{3,342,3};
		return "success";
	}

}
