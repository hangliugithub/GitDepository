package priv.jesse.cloudnote.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
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

//@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	public AccountController() {
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(String name,String password,String code,HttpServletRequest request,HttpServletResponse response){
		try {
			String serverCode = (String) request.getSession().getAttribute("code");
			//System.out.println(serverCode+"；"+code);
			if(serverCode==null || !serverCode.equalsIgnoreCase(code)){
				return new JsonResult<User>("验证码无效");
			}
//			if(!serverCode.equalsIgnoreCase(code)){
//				return new JsonResult<User>("验证码错误！");
//			}
			
			User user = userService.login(name, password);
			//保存cookie token
			//利用User-Agent创建token
			String userAgent = request.getHeader("User-Agent");
			long now = System.currentTimeMillis();
			String token = Md5.saltMd5(userAgent+now);
			Cookie cookie = new Cookie("token",now+"|"+token);
			cookie.setPath("/");
			cookie.setMaxAge(7*24*60*60);
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

	@RequestMapping("/logout.do")
	@ResponseBody
	public void loginout(HttpServletRequest request,HttpServletResponse response){
		//删除所有cookie
		Cookie[] cookies = request.getCookies();
		try {
			if(cookies==null || cookies.length==0){
				response.sendRedirect("/CloudNote/login.html");
			}
			for(Cookie c : cookies){
				System.out.println(c.getName()+":"+c.getValue());
				
				if("token".equals(c.getName()))
					c.setMaxAge(0);
				
			}
			response.sendRedirect("/CloudNote/login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/checkCode.do")
	@ResponseBody
	public JsonResult<Boolean> checkCode(String code,HttpServletRequest request){
		String serverCode = (String) request.getSession().getAttribute("code");
		System.out.println(code);
		System.out.println(serverCode);
		if(serverCode==null){
			return new JsonResult<Boolean>("登录失败！请重试");
		}
		if(serverCode.equalsIgnoreCase(code)){
			return new JsonResult<Boolean>(true);
		}
		return new JsonResult<Boolean>("验证码错误！");
	}
	
	
	/**
	 * 生成验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/code.do", produces="image/png")
	@ResponseBody
	public byte[] code(HttpServletRequest request){
	    byte[] buf;
	    try{
	        //创建图片对象
	        BufferedImage img = 
	            new BufferedImage(80, 30, 
	            BufferedImage.TYPE_3BYTE_BGR);
	        //填充背景为 灰色
	        for(int y=0; y<img.getHeight();y++){
	            for(int x=0; x<img.getWidth();x++){
	                img.setRGB(x, y, 0xEEEEEE); 
	            }
	        }
	        //绘制500个麻点
	        for(int i=0; i<500; i++){
	            int x=(int)(Math.random()*80);
	            int y=(int)(Math.random()*30);
	            int rgb=(int)(Math.random()*0xffffff);
	            img.setRGB(x, y, rgb); 
	        }
	        //获取画笔对象
	        Graphics2D g = img.createGraphics();
	        //随机色
	        int rgb=(int)(Math.random()*0xffffff);
	        g.setColor(new Color(rgb));
	        //设置字体大小
	        Font font = new Font(Font.SANS_SERIF, 
	                Font.BOLD, 25);
	        g.setFont(font); 

	        //抗锯齿，平滑绘制
	        g.setRenderingHint(
	            RenderingHints.KEY_RENDERING, 
	            RenderingHints.VALUE_RENDER_QUALITY);
	        //生成验证码         
	        String code = randomCode();
	        //将验证码保存到session
	        request.getSession().setAttribute("code", code);
	        //绘制验证码
	        int x=(int)(Math.random()*10);
	        int y=(int)(Math.random()*5);
	        g.drawString(code, 3+x, 28-y);
	        //绘制5条乱线
	        for(int i=0; i<5; i++){
	            int x1=(int)(Math.random()*80);
	            int x2=(int)(Math.random()*80);
	            int y1=(int)(Math.random()*30);
	            int y2=(int)(Math.random()*30);
	            rgb=(int)(Math.random()*0xffffff);
	            g.setColor(new Color(rgb));
	            g.drawLine(x1, y1, x2, y2); 
	        }
	        //利用ByteArrayOutputStream获取png编码数据
	        ByteArrayOutputStream out=
	            new ByteArrayOutputStream();
	        ImageIO.write(img, "png", out);
	        out.close();
	        buf = out.toByteArray();
	        return buf;
	    }catch(Exception e){
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    }
	}

	private String randomCode() {
	    String chs="4567ABCDEFHJKLXYabcdrhknp";
	    char[] code = new char[4];
	    for(int i = 0; i<code.length; i++){
	        int index=(int)(Math.random()*chs.length());
	        code[i]=chs.charAt(index);
	    }
	    return new String(code);
	}
}














