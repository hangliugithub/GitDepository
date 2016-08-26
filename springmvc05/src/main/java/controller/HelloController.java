package controller;

import org.springframework.stereotype.Controller;
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
}
