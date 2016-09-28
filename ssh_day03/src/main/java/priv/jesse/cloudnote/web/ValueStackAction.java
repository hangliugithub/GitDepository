package priv.jesse.cloudnote.web;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import priv.jesse.cloudnote.entity.User;

@Component
@Scope("prototype")
public class ValueStackAction {
	private String message;
	private int age;
	
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ValueStackAction() {}

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
	
	public String execute(){
		message = "hello world";
		age = 22;
		user = new User("dsjkafl", "catalina", "123456", "", "");
		ActionContext act = ActionContext.getContext();
		act.getValueStack().push(user);
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
		
		return "success";
	}
}
