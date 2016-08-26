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
		try{
			Admin admin = service.checkLogin(adminCode, password);
			//��¼�ɹ�����admin����󶨵�session�����ϣ�����session��֤
			session.setAttribute("admin", admin);
		}catch(Exception e){
			e.printStackTrace();
			if(e instanceof ApplicationException){
				//Ӧ���쳣����ȷ��ʾ�û�
				String message = e.getMessage();
				req.setAttribute("error", message);
				return "main/login";
			}else{
				return "error";
			}
		}
		//��¼�ɹ����ض�����ҳ
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	
}












