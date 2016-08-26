package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 * 处理器类
 * http://ip:port/demo/hello.do
 * @author Jesse
 *	
 */
@Controller
//@RequestMapping("/domo")
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("hello()");
		//返回一个视图名
		return new String("hello");
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String checkLogin(HttpServletRequest req,HttpServletResponse res ) throws ServletException, IOException{
		System.out.println("checkLogin()");
		
		req.setCharacterEncoding("utf-8");
		String userCode = req.getParameter("userCode");
		String password = req.getParameter("password");
		System.out.println(userCode+":"+password);
		req.setAttribute("userCode", userCode);
		req.setAttribute("password", password);
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
		
		return "index";
	}
	
	@RequestMapping("/login2.do")
	public String checkLogin2(String userCode,@RequestParam("password")String pwd){
		System.out.println("checkLogin2()");
		System.out.println(userCode+":"+pwd);
		return "index";
	}
	
	@RequestMapping("/login3.do")
	public String checkLogin3(Admin admin){
		System.out.println("checkLogin3()");
		System.out.println(admin.getUserCode()+":"+admin.getPassword());
		return "index";
	}
	
	/**
	 * 向页面传值的第一种方式：使用request对象
	 * @return
	 */
	@RequestMapping("/login4.do")
	public String checkLogin4(Admin admin,HttpServletRequest req){
		System.out.println("checkLogin4()");
		System.out.println(admin.getUserCode()+":"+admin.getPassword());
		req.setAttribute("userCode", admin.getUserCode());
		req.setAttribute("password", admin.getPassword());
		//springmvc默认使用转发机制来跳转页面
		return "index";
	}
	
	/**
	 * 向页面传值的第二种方法：使用ModelAndView
	 */
	@RequestMapping("/login5.do")
	public ModelAndView checkLogin5(Admin admin){
		System.out.println("checkLogin5()");
		Map<String,String> m = new HashMap<String,String>();
		//相当于执行了request.setAttribute
		m.put("userCode", admin.getUserCode());
		m.put("password",admin.getPassword());
		return new ModelAndView("index",m);
	}
	
	/**
	 * 向页面传值的第三种方法：使用ModelMap
	 */
	@RequestMapping("/login6.do")
	public String checkLogin6(Admin admin,ModelMap mm){
		System.out.println("checkLogin6()");
		//相当于执行了request.setAttribute
		mm.addAttribute("userCode",admin.getUserCode());
		mm.addAttribute("password", admin.getPassword());
		return "index";
	}
	
	/**
	 * 向页面传值的第四种方法：使用session
	 */
	@RequestMapping("/login7.do")
	public String checkLogin7(Admin admin,HttpSession session){
		System.out.println("checkLogin7()");
		session.setAttribute("userCode", admin.getUserCode());
		session.setAttribute("password", admin.getPassword());
		return "index";
	}
	
	/**
	 * 重定向方式1
	 */
	@RequestMapping("/login8.do")
	public String checkLogin8(){
		System.out.println("checkLogin8()");
		return "redirect:toIndex.do";
	}
	/**
	 * 重定向方式2
	 * @return
	 */
	@RequestMapping("/login9.do")
	public ModelAndView checkLogin9(){
		System.out.println("checkLogin9()");
		RedirectView rv = new RedirectView("toIndex.do");
		ModelAndView rav = new ModelAndView(rv);
		return rav;
	}
	
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
}






















