package controller;
/**
 * 这个类用于封装请求参数
 * @author Jesse
 *
 */
public class Admin {
	private String userCode;
	private String password;
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(String userCode, String password) {
		super();
		this.userCode = userCode;
		this.password = password;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [userCode=" + userCode + ", password=" + password + "]";
	}
	
}	
