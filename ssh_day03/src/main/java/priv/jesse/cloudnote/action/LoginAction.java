package priv.jesse.cloudnote.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction implements JsonAction<User> {
	
	private String username;
	private String password;
	private JsonResult<User> result;
	
	@Autowired
	private UserService userService;
	
//	public UserService getUserService() {
//		return userService;
//	}
	
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see priv.jesse.cloudnote.web.JsonAction#setUsername(java.lang.String)
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see priv.jesse.cloudnote.web.JsonAction#getResult()
	 */
	public JsonResult<User> getResult() {
		return result;
	}
	
	/* (non-Javadoc)
	 * @see priv.jesse.cloudnote.web.JsonAction#setResult(priv.jesse.cloudnote.web.JsonResult)
	 */
	/* (non-Javadoc)
	 * @see priv.jesse.cloudnote.web.JsonAction#setResult(priv.jesse.cloudnote.web.JsonResult)
	 */
	public void setResult(JsonResult<User> result) {
		this.result = result;
	}
	
	public LoginAction() {
	}
	
	public String execute(){
//		System.out.println(username+","+password);
//		System.out.println(userService);
		User user =  userService.login(username, password);
		result = new JsonResult<User>(user);
		
		return "success";
	}

	
	

}
