package priv.jess.cloudnote.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
//@Aspect//���ڱ�ʾ�����ʱ�������
public class DemoAspect {

	public DemoAspect() {
	}
	
	//@Before("within(priv.jesse.cloudnote.web.AccountController)")
	//@Before("execution(* String priv.jesse.cloudnote.web.AccountController.randomCode())")
	
	public void hello(){
		System.out.println("hello world");
	}

	
	//--------------------------------
	
}
