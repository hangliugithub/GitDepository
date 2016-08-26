package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Fileter在tomcat启动时自动实例化和初始化，关闭时自动销毁
 * 即：Fileter是单例的
 * @author Jesse
 *
 */
public class FirstFilter implements Filter {

	public void destroy() {
		System.out.println("销毁FirstFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("First前");
		
		//1.调用此方法则请求继续
		//2.不掉用此方法则请求中断
		chain.doFilter(req, res);//让请求继续执行
		
		
		System.out.println("First后");
		

	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化FirstFilter");
	}

}
