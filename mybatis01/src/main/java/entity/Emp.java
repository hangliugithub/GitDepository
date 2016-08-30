package entity;

public class Emp {
	private Integer id;
	private String name;
	private Integer age;

	public Emp() {
		// TODO Auto-generated constructor stub
	}

	public Emp(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
