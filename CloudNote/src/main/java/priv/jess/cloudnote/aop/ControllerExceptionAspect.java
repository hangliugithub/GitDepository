package priv.jess.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import priv.jesse.cloudnote.web.JsonResult;

/**
 * 统一处理控制器异常
 * @author Jesse
 *
 */
@Component
@Aspect//控制器异常处理切面
public class ControllerExceptionAspect {

	public ControllerExceptionAspect() {
	}
	
	//@Around("bean(accountController2)||bean(notebookController)")
	@Around("bean(accountController2)")
	public Object process(ProceedingJoinPoint joinPoint){
		try {
			System.out.println("开始调用控制器方法");
			Object obj = joinPoint.proceed();
			System.out.println("方法正常结束");
			return obj;
		} catch (Throwable e) {
			//目标方法执行出现异常
			System.out.println("异常：");
			e.printStackTrace();
			return new JsonResult<Object>(JsonResult.ERROR,e.getMessage());
		}
	}

}
