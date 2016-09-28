package priv.jesse.cloudnote.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import priv.jesse.cloudnote.action.JsonResult;

public class ExceptionInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -552066254515047970L;
	

	public ExceptionInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			String val = invocation.invoke();
			System.out.println("Õý³£");
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			JsonResult result = new JsonResult(1,e.getMessage());
		}
		return null;
	}

}
