package priv.jesse.web;

public class LoginAction {

	private String username;
	private String password;
	private String message;
	
	public LoginAction() {
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String execute(){
		toString();
		if("Catalina".equalsIgnoreCase(username) && "123".equals(password)){
			return "success";
		}
		message="用户名或密码错误！";
		return "error";
	}

	@Override
	public String toString() {
		return "LoginAction [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
