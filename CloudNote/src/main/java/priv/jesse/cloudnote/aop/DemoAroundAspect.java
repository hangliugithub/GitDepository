package priv.jesse.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class DemoAroundAspect {

	public DemoAroundAspect() {
		// TODO Auto-generated constructor stub
	}
	
	//@Around("bean(demoBean)")
	@Around("execution(* priv.jess.cloudnote.aop.DemoBean.demo(..))")
	public Object execute(ProceedingJoinPoint point){
		try {
			System.out.println("��ʼ");
			Object obj = point.proceed();//ִ�б������Ŀ�귽��
			System.out.println("����");
			return obj;
		} catch (Throwable e) {
			//e.printStackTrace();
			System.out.println("���쳣:"+e.getMessage());
			return null;
		}
	}

}
