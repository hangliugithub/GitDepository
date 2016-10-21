package priv.jess.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import priv.jesse.cloudnote.web.JsonResult;

/**
 * ͳһ����������쳣
 * @author Jesse
 *
 */
@Component
@Aspect//�������쳣��������
public class ControllerExceptionAspect {

	public ControllerExceptionAspect() {
	}
	
	//@Around("bean(accountController2)||bean(notebookController)")
	@Around("bean(accountController2)")
	public Object process(ProceedingJoinPoint joinPoint){
		try {
			System.out.println("��ʼ���ÿ���������");
			Object obj = joinPoint.proceed();
			System.out.println("������������");
			return obj;
		} catch (Throwable e) {
			//Ŀ�귽��ִ�г����쳣
			System.out.println("�쳣��");
			e.printStackTrace();
			return new JsonResult<Object>(JsonResult.ERROR,e.getMessage());
		}
	}

}
