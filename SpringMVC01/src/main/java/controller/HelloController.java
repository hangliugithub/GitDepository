package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelloController's handleRequest()");
		/*
		 * ModelAndView有两个构造器
		 * ModelAndView(String viewName)
		 * ModelAndView(String viewName,Map data) data处理结果
		 */
		return new ModelAndView("hello");
	}

	
}
