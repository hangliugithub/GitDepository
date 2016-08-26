package beans1;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	private char gender;
	private String idNum;
	private List<String> interest;
	private Properties userInfo;
	private Computer pc;
	
	public Computer getPc() {
		return pc;
	}

	
	
	public Student(String name, int age, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}



	public void setPc(Computer pc) {
		this.pc = pc;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public Properties getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Properties userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + ", idNum=" + idNum + ", interest="
				+ interest + ", userInfo=" + userInfo + ", pc=" + pc + "]";
	}
	
	

}
