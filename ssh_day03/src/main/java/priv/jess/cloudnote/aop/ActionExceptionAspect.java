package priv.jess.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import priv.jesse.cloudnote.action.JsonAction;
import priv.jesse.cloudnote.action.JsonResult;
import priv.jesse.cloudnote.entity.User;

/**
 * ͳһ����������쳣
 * @author Jesse
 *
 */
//@Component
//@Aspect//�������쳣��������
public class ActionExceptionAspect {

	
	public ActionExceptionAspect() {
	}
	
	//@Around("bean(accountController2)||bean(notebookController)")

	@Around("execution(* priv.jesse.cloudnote.web.*Action.execute())")
	public Object process(ProceedingJoinPoint joinPoint){
		Object action = joinPoint.getTarget(); 
		try {
			//System.out.println("��ʼ���ÿ���������");
			Object obj = joinPoint.proceed();
			//System.out.println("������������");
			return (String)obj;
		} catch (Throwable e) {
			//Ŀ�귽��ִ�г����쳣
			e.printStackTrace();
			if(action instanceof JsonAction){
				System.out.println("�쳣��");
				((JsonAction<User>) action).setResult(new JsonResult<User>(JsonResult.ERROR,e.getMessage()));
			}
			return "error";
		}
	}

}
