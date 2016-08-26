package annotationtest;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
@Component("mgr")
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Computer computer;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(Computer computer) {
		super();
		this.computer = computer;
	}

	public Computer getComputer() {
		return computer;
	}

	@Resource(name="computer")
	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	@Override
	public String toString() {
		return "Manager [computer=" + computer + "]";
	}
	
	
	

}
