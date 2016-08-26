package ioc;

/**
 * ÑİÊ¾ÒÀÀµ×¢Èë
 * 
 * @author Jesse
 *
 */
public class A {
	private B b;
	
	public void setB(B b){
		System.out.println("A's setB()");
		this.b = b;
	}
	
	public B getB() {
		return b;
	}
	
	public A() {
		System.out.println("A's A()");
	}

	public void execute() {
		// B b = new B();
		// b.f1();
		b.f1();
	}

}
