package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SomeInterceptor implements HandlerInterceptor {

	/*
	 * 最后执行的方法。
	 * ex是一个异常对象，是处理器对象所抛出的异常
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion()");

	}

	/*
	 * 拦截器方法已经执行完毕，正准备将处理结果（ModelAndView）返回给前端控制器时侯，
	 * 执行该方法，我们可以在这里修改ModelAndView
	 * 
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle()");
	}
	/*
	 * 前端控制器收到请求后，会先调用拦截器的preHandle方法，如果该方法的返回值为true，表示继续向后调用
	 * 如果返回false，表示中断请求
	 * arg2：是一个对象，对应处理器的方法
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle()");
		return true;
	}

}
