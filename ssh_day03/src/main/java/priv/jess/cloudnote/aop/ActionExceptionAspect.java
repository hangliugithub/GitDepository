package priv.jess.cloudnote.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import priv.jesse.cloudnote.action.JsonAction;
import priv.jesse.cloudnote.action.JsonResult;
import priv.jesse.cloudnote.entity.User;

/**
 * 统一处理控制器异常
 * @author Jesse
 *
 */
//@Component
//@Aspect//控制器异常处理切面
public class ActionExceptionAspect {

	
	public ActionExceptionAspect() {
	}
	
	//@Around("bean(accountController2)||bean(notebookController)")

	@Around("execution(* priv.jesse.cloudnote.web.*Action.execute())")
	public Object process(ProceedingJoinPoint joinPoint){
		Object action = joinPoint.getTarget(); 
		try {
			//System.out.println("开始调用控制器方法");
			Object obj = joinPoint.proceed();
			//System.out.println("方法正常结束");
			return (String)obj;
		} catch (Throwable e) {
			//目标方法执行出现异常
			e.printStackTrace();
			if(action instanceof JsonAction){
				System.out.println("异常：");
				((JsonAction<User>) action).setResult(new JsonResult<User>(JsonResult.ERROR,e.getMessage()));
			}
			return "error";
		}
	}

}
