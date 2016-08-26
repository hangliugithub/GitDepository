package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HelloController {
	@RequestMapping("/hello1.do")
	public String hello1(){
		System.out.println("hello1()");
		String num = "123s";
		Integer.parseInt(num);
		return "hello";
	}
	
	@RequestMapping("/hello2.do")
	public String hello2(){
		System.out.println("hello2()");
		String str = "asdfnfv";
		str.charAt(9);
		return "hello";
	}
	
	/*
	 * 这是一个异常处理方法，专门用来处理其他方法抛出的异常
	 * 参数：ex 是其他方法抛出的异常 
	 */
	@ExceptionHandler
	public String execute(Exception ex,HttpServletRequest req){
		if(ex instanceof NumberFormatException){
			req.setAttribute("errMsg", "Dear，please input right number！");
			return "error";
		}
		if(ex instanceof StringIndexOutOfBoundsException){
			req.setAttribute("errMsg", "String Index Out Of Bounds！");
			return "error";
		}
		return "error2";
	}
}
