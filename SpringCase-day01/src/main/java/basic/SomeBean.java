package basic;
/**
 * 让容器创建一个Some Bean实例（必须提供初始化方法和销毁方法）
 * @author Jesse
 *
 */
public class SomeBean {
	public SomeBean() {
		System.out.println("实例化SomeBean...");
	}
	public void init(){
		System.out.println("初始化SomeBean...");
	}
	public void destroy(){
		System.out.println("销毁SomeBean...");
	}
}
