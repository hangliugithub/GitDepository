package annotation;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * ʹ��@Autowired��@Qualifiedע��������ϵ��������ע�룩
 * @author Jesse
 *
 */
@Component("school")
public class School implements Serializable {

	private static final long serialVersionUID = 1L;

	private Waitress wt;
	public School() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public School(@Qualifier("wt")Waitress wt) {
		System.out.println("School's School(Waitress wt)");
		this.wt = wt;
	}
	@Override
	public String toString() {
		return "School [wt=" + wt + "]";
	}
	
}
