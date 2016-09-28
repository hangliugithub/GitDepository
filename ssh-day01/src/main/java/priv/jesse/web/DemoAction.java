package priv.jesse.web;

public class DemoAction {

	private String name;
	
	private static int num = 8;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DemoAction() {
		System.out.println(Thread.currentThread().getName()+":创建了一个DemoAction对象");
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String execute(){
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+this);
		return "success";
	}

	@Override
	public String toString() {
		return "DemoAction [name=" + name + ", num=" + num + "]";
	}
	

}
