package ioc;
/**
 * 演示构造器注入
 * @author Jesse
 *
 */
public class A {
	private B b;
	
	public A() {
		System.out.println("A's A()");
	}
	
	public A(B b){
		this.b = b;
		System.out.println("A's A(B b)");
	}
	
	public void execute(){
		b.f1();
	}
}
