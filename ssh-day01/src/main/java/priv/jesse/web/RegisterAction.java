package priv.jesse.web;

import priv.jesse.entity.User;

public class RegisterAction {
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RegisterAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute(){
		System.out.println(user);
		return "success";
	}
}
