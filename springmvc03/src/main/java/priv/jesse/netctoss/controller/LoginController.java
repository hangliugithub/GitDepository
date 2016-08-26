package priv.jesse.netctoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.netctoss.entity.Admin;
import priv.jesse.netctoss.service.ApplicationException;
import priv.jesse.netctoss.service.LoginService;

/**
 *  处理器类（二级处理器）
 * @author Jesse
 *
 */
@Controller
public class LoginController {
	@Resource(name="loginService")
	private LoginService service;
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "main/login";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req,HttpSession session){
		
		//读取账号和密码
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		System.out.println(adminCode+":"+password);
		
		//暂时不做参数值合法性检查
		try{
			Admin admin = service.checkLogin(adminCode, password);
			//登录成功将将admin对象绑定到session对象上，用于session验证
			session.setAttribute("admin", admin);
		}catch(Exception e){
			e.printStackTrace();
			if(e instanceof ApplicationException){
				//应用异常，明确提示用户
				String message = e.getMessage();
				req.setAttribute("error", message);
				return "main/login";
			}else{
				return "error";
			}
		}
		//登录成功，重定向到首页
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	
}












