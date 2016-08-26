package annotationtest;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("computer")
public class Computer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String model;
	
	//@Value("#{conf.username}")
	private String username;
	//@Value("#{conf.password}")
	private String password;
	
	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public Computer(String model) {
		super();
		this.model = model;
	}

	
	@Autowired
	public Computer(@Value("SurfaceBook")String model, @Value("#{conf.username}")String username, @Value("#{conf.password}")String password) {
		super();
		this.model = model;
		this.username = username;
		this.password = password;
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

	public String getModel() {
		return model;
	}

	//@Value("SurfaceBook")
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Computer [model=" + model + ", username=" + username + ", password=" + password + "]";
	}
	

}
