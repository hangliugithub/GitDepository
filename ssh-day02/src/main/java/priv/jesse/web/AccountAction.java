package priv.jesse.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class AccountAction {

	private String username;
	private String password;
	
	private JsonResult<List<String>> data;
	
	public JsonResult<List<String>> getData() {
		return data;
	}

	public void setData(JsonResult<List<String>> data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String execute(){
		if("catalina".equals(username) && "123".equals(password)){
			List<String> list = new ArrayList<String>();
			list.add(username);
			list.add(password);
			data = new JsonResult<List<String>>(list);
			return "success";
		}else{
			data = new JsonResult<List<String>>("µÇÂ¼Ê§°Ü£¡");
			return "error";
		}
	}

}
