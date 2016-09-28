package priv.jesse.cloudnote.web;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
@Component
public class DemoInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -7630384252821766916L;

	public DemoInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("��ʼ...");
		//���ú�����Action
		String val = invocation.invoke();
		System.out.println("����.");
		return val;
	}

}
