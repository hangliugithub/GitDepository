package cont;

public class Info {
	private double height;
	private double weight;
	private double bmi;
	private String type;
	
	public Info() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Info(double height, double weight, double bmi, String type) {
		super();
		this.height = height;
		this.weight = weight;
		this.bmi = bmi;
		this.type = type;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	@Override
	public String toString() {
		return "Info [height=" + height + ", weight=" + weight + ", bmi=" + bmi + ", type=" + type + "]";
	}
	
}
