package web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SecondFilter
 */
public class SecondFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecondFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("����SecondFilter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Secondǰ");
		
		chain.doFilter(request, response);
		
		System.out.println("Second��");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig conf) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("��ʼ��SecondFilter");
		String city = conf.getInitParameter("city");
		System.out.println("city"+":"+city);
		
	}

}
