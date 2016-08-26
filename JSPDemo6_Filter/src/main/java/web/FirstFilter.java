package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Fileter��tomcat����ʱ�Զ�ʵ�����ͳ�ʼ�����ر�ʱ�Զ�����
 * ����Fileter�ǵ�����
 * @author Jesse
 *
 */
public class FirstFilter implements Filter {

	public void destroy() {
		System.out.println("����FirstFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Firstǰ");
		
		//1.���ô˷������������
		//2.�����ô˷����������ж�
		chain.doFilter(req, res);//���������ִ��
		
		
		System.out.println("First��");
		

	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("��ʼ��FirstFilter");
	}

}
