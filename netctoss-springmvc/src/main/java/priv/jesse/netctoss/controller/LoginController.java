package priv.jesse.netctoss.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.netctoss.entity.Admin;
import priv.jesse.netctoss.service.ApplicationException;
import priv.jesse.netctoss.service.LoginService;
import priv.jesse.netctoss.utils.ImageUtil;

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
		String validCode = req.getParameter("valicode");
		String realCode = (String) session.getAttribute("valicode");
		//暂时不做参数值合法性检查
		try{
			if(realCode.equalsIgnoreCase(validCode)){
				Admin admin = service.checkLogin(adminCode, password);
				session.setAttribute("admin", admin);
			}else{
				req.setAttribute("error", "验证码错误");
				return "main/login";
			}
			
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
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.removeAttribute("admin");
		return "rediract:/netctoss-springmvc/toLogin.do";
		
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	
	@RequestMapping("/createImg.do")
	public void createImg(HttpSession session,HttpServletResponse res) throws IOException{
		Object[] objs = ImageUtil.createImage();
		session.setAttribute("valicode", objs[0]);
		//输出验证码图片
		res.setContentType("image/png");
		OutputStream out = res.getOutputStream();
		ImageIO.write((BufferedImage)objs[1], "png", out);
		out.close();
	}
	
}












