package ioc;

import java.io.Serializable;

public class Restaurant implements Serializable{

	private static final long serialVersionUID = 1L;
	private Waitress wr;
	public Restaurant() {
		System.out.println("Restaurant's Restaurant()");
	}
	
	
	public Restaurant(Waitress wr) {
		super();
		this.wr = wr;
	}


	public void setWr(Waitress wr){
		this.wr = wr ;
	}
	public Waitress getW(){
		return wr;
	}
	public void work(){
		wr.serve();
	}
	@Override
	public String toString() {
		return "Restaurant [w=" + wr + "]";
	}
	
	
}
