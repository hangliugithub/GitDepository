package priv.jesse.cloudnote.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;
import priv.jesse.cloudnote.util.Md5;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	public AccountController() {
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(String name,String password,HttpServletRequest request,HttpServletResponse response){
		try {
			User user = userService.login(name, password);
			//保存cookie token
			//利用User-Agent创建token
			String userAgent = request.getHeader("User-Agent");
			long now = System.currentTimeMillis();
			String token = Md5.saltMd5(userAgent+now);
			Cookie cookie = new Cookie("token",now+"|"+token);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			return new JsonResult<User>(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<User>(e.getMessage());
		}
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult<User> regist(String name,String password,String nick){
		//System.out.println("name:"+name+",password:"+password+",nick"+nick);
		try {
			User user = userService.regist(name, password, nick);
			return new JsonResult<User>(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<User>(e.getMessage());
		}
	}

}














