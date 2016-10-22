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
			System.out.println("开始");
			Object obj = point.proceed();//执行被代理的目标方法
			System.out.println("结束");
			return obj;
		} catch (Throwable e) {
			//e.printStackTrace();
			System.out.println("有异常:"+e.getMessage());
			return null;
		}
	}

}
