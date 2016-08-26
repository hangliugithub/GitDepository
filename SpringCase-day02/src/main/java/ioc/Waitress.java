package ioc;

import java.io.Serializable;

public class Waitress implements Serializable{

	private static final long serialVersionUID = 1L;
	public Waitress(){
		System.out.println("Waitress's Waitress()");
	}
	public void serve(){
		System.out.println("Waitress's serve()");
	}
	
}
