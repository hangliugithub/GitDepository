package beans2;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("stu")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Value("Jesse")
	private String name;
	@Value("22")
	private int age;
	@Value("ÄÐ")
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


	@Autowired
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
	
	@Resource(name="conf")
	public void setUserInfo(Properties userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + ", idNum=" + idNum + ", interest="
				+ interest + ", userInfo=" + userInfo + ", pc=" + pc + "]";
	}
	
	

}
