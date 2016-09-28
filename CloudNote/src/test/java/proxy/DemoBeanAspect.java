package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * �൱��AOP���档����չ���ܵ���
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
	 *	proxy:����ӿ�
	 *	method:������
	 *	args:��������
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("������ʼ...");
		//ִ��Ŀ�귽��
		Object obj = method.invoke(target, args);
		System.out.println("����������");
		return obj;
	}

}
