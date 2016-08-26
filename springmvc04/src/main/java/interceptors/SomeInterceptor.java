package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SomeInterceptor implements HandlerInterceptor {

	/*
	 * ���ִ�еķ�����
	 * ex��һ���쳣�����Ǵ������������׳����쳣
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion()");

	}

	/*
	 * �����������Ѿ�ִ����ϣ���׼������������ModelAndView�����ظ�ǰ�˿�����ʱ�
	 * ִ�и÷��������ǿ����������޸�ModelAndView
	 * 
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle()");
	}
	/*
	 * ǰ�˿������յ�����󣬻��ȵ�����������preHandle����������÷����ķ���ֵΪtrue����ʾ����������
	 * �������false����ʾ�ж�����
	 * arg2����һ�����󣬶�Ӧ�������ķ���
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle()");
		return true;
	}

}
