package priv.jesse.cloudnote.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class DemoBeanAspect {

	public DemoBeanAspect() {
		// TODO Auto-generated constructor stub
	}

	@Before("bean(demoBean)")
	public void start() {
		System.out.println("开始");
	}

	@AfterReturning("bean(demoBean)")
	public void end() {
		System.out.println("方法结束");
	}

	// throwing="e" 用于接收方法抛出的异常对象
	// 并且会自动注入到参数 Throwable e
	@AfterThrowing(throwing = "e", pointcut = "bean(demoBean)")
	public void error(Throwable e) {
		System.out.println("异常" + e.getMessage());
	}

	@After("bean(demoBean)")
	public void over() {
		System.out.println("最终");
	}

}
