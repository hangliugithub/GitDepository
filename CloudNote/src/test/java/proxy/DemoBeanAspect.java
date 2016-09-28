package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 相当于AOP切面。是扩展功能的类
 * @author Jesse
 *
 */
public class DemoBeanAspect implements InvocationHandler {
	
	private DemoBeanIf target;
	
	public DemoBeanAspect(DemoBeanIf target) {
		this.target = target;
	}

	/**
	 *	@param
	 *	proxy:代理接口
	 *	method:代理方法
	 *	args:方法参数
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("方法开始...");
		//执行目标方法
		Object obj = method.invoke(target, args);
		System.out.println("方法结束。");
		return obj;
	}

}
