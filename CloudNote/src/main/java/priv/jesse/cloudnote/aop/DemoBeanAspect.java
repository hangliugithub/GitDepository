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
		System.out.println("��ʼ");
	}

	@AfterReturning("bean(demoBean)")
	public void end() {
		System.out.println("��������");
	}

	// throwing="e" ���ڽ��շ����׳����쳣����
	// ���һ��Զ�ע�뵽���� Throwable e
	@AfterThrowing(throwing = "e", pointcut = "bean(demoBean)")
	public void error(Throwable e) {
		System.out.println("�쳣" + e.getMessage());
	}

	@After("bean(demoBean)")
	public void over() {
		System.out.println("����");
	}

}
