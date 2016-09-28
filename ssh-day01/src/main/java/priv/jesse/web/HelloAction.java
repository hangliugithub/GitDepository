package priv.jesse.web;

import java.util.ArrayList;
import java.util.List;

/**
 * struts2 控制器方法可以支持任意的类
 * 一般控制方法是execute
 * 返回值是String 对应的是最终名字 如：success error
 * 在配置文件中将名字映射到视图上
 * success -> /WEB-INF/ok.jsp
 * @author Jesse
 *
 */
public class HelloAction {

	private String message;
	
	private List<String> names;
	
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String execute(){
		System.out.println(Thread.currentThread().getName());
		message = "hello world";
		names = new ArrayList<String>();
		names.add("Catalina");
		names.add("Tom");
		names.add("Andy");
		System.out.println("hello world");
		return "success";
	}

}
