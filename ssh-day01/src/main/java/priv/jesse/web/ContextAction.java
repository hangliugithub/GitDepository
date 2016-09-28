package priv.jesse.web;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Jesse
 *
 */
public class ContextAction implements SessionAware,RequestAware,ApplicationAware{

	private Map<String,Object> session;
	
	private Map<String,Object> request;
	
	private Map<String,Object> application;
	
	
	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ContextAction() {
	}

	/**
	 *  π”√request,response,session,application
	 * @return
	 */
	public String execute(){
		session.put("loginUser", "Tom");
		request.put("message", "22");
		application.put("count", 5);
		//ctx 
//		HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request =  ServletActionContext.getRequest();
//		ServletContext context = ServletActionContext.getServletContext();
		System.out.println(request);
		System.out.println(application);
		System.out.println(session);
		return "success";
	}
}
