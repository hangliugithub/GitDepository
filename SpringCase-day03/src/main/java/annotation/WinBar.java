package annotation;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 使用@Resource来注入依赖关系
 * @author Jesse
 *
 */
@Component("wb")
public class WinBar implements Serializable {

	private static final long serialVersionUID = 1L;
	//@Resource(name="wt")
	private Waitress wt;
	//@Value("#{conf.pageSize}")
	private String pageSize;
	//@Value("浙江省杭州市")
	private String address;
	
	public WinBar() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getAddress() {
		return address;
	}

	@Value("浙江省杭州市")
	public void setAddress(String address) {
		this.address = address;
	}


	public String getPageSize() {
		return pageSize;
	}

	@Value("#{conf.pageSize}")
	public void setPageSize(String pageSize) {
		System.out.println("WinBar's setPageSize()");
		this.pageSize = pageSize;
	}


	public WinBar(Waitress wt) {
		super();
		this.wt = wt;
	}

	public Waitress getWt() {
		return wt;
	}
	
	@Resource(name="wt")
	public void setWt(Waitress wt) {
		System.out.println("WinBar's setWt()");
		this.wt = wt;
	}

	@Override
	public String toString() {
		return "WinBar [wt=" + wt + ", pageSize=" + pageSize + ", address=" + address + "]";
	}
	
	

}
