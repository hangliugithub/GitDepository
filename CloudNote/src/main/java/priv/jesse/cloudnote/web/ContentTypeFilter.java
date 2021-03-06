package priv.jesse.cloudnote.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContentTypeFilter implements Filter {
	private String contentType;
	
	public ContentTypeFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.setContentType(contentType);
		//System.out.println(contentType);
		chain.doFilter(req, res);
	}

	public void init(FilterConfig cfg) throws ServletException {
		contentType = cfg.getInitParameter("contentType");

	}

}
