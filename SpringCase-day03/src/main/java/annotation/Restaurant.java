package annotation;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *  π”√@Autowire∫Õ@Qualifier
 * @author Jesse
 *
 */
@Component("rest")
public class Restaurant implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	@Autowired
//	@Qualifier("wt")
	private Waitress wt;
	
	
	public Restaurant() {
		System.out.println("Restaurant's Restaurant()");
	}
	public Waitress getWaitress() {
		return wt;
	}
	
	@Autowired
	public void setWt(@Qualifier("wt")Waitress wt) {
		System.out.println("Restaurant's setWt()");
		this.wt = wt;
	}
	@Override
	public String toString() {
		return "Restaurant [wt=" + wt + "]";
	}
	
}
