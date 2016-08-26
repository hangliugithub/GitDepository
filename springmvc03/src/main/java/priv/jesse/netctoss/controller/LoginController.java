package priv.jesse.netctoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.netctoss.entity.Admin;
import priv.jesse.netctoss.service.ApplicationException;
import priv.jesse.netctoss.service.LoginService;

/**
 *  �������ࣨ������������
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
		
		//��ȡ�˺ź�����
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		System.out.println(adminCode+":"+password);
		
		//��ʱ��������ֵ�Ϸ��Լ��
		Admin admin = service.checkLogin(adminCode, password);
		//��¼�ɹ�����admin����󶨵�session�����ϣ�����session��֤
		session.setAttribute("admin", admin);
		//��¼�ɹ����ض�����ҳ
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	
	@ExceptionHandler
	public String execute(Exception ex,HttpServletRequest req){
		if(ex instanceof ApplicationException){
			req.setAttribute("error", ex.getMessage());
			return "main/login";
		}else{
			return "error";
		}
	}
}












