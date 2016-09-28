package priv.jesse.cloudnote.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction implements JsonAction<User> {
	
	private String name;
	private String password;
	private JsonResult<User> result;
	
	@Autowired
	private UserService userService;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JsonResult<User> getResult() {
		return result;
	}
	
	public void setResult(JsonResult<User> result) {
		this.result = result;
	}
	
	public LoginAction() {}
	
	public String execute(){
//		System.out.println(username+","+password);
//		System.out.println(userService);
		User user =  userService.login(name, password);
		result = new JsonResult<User>(user);
		return "success";
	}

	
	private InputStream img;
	public InputStream getImg() {
		return img;
	}
	public void setImg(InputStream img) {
		this.img = img;
	}
	/**
	 * ������֤��
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String code() throws IOException {
		byte[] buf;
		// ����ͼƬ����
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_3BYTE_BGR);
		// ��䱳��Ϊ ��ɫ
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				img.setRGB(x, y, 0xEEEEEE);
			}
		}
		// ����500�����
		for (int i = 0; i < 500; i++) {
			int x = (int) (Math.random() * 80);
			int y = (int) (Math.random() * 30);
			int rgb = (int) (Math.random() * 0xffffff);
			img.setRGB(x, y, rgb);
		}
		// ��ȡ���ʶ���
		Graphics2D g = img.createGraphics();
		// ���ɫ
		int rgb = (int) (Math.random() * 0xffffff);
		g.setColor(new Color(rgb));
		// ���������С
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		g.setFont(font);

		// ����ݣ�ƽ������
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		// ������֤��
		String code = randomCode();
		// ����֤�뱣�浽session
		ServletActionContext.getRequest().getSession().setAttribute("code", code);
		// ������֤��
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 5);
		g.drawString(code, 3 + x, 28 - y);
		// ����5������
		for (int i = 0; i < 5; i++) {
			int x1 = (int) (Math.random() * 80);
			int x2 = (int) (Math.random() * 80);
			int y1 = (int) (Math.random() * 30);
			int y2 = (int) (Math.random() * 30);
			rgb = (int) (Math.random() * 0xffffff);
			g.setColor(new Color(rgb));
			g.drawLine(x1, y1, x2, y2);
		}
		// ����ByteArrayOutputStream��ȡpng��������
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		out.close();
		buf = out.toByteArray();
		this.img = new ByteArrayInputStream(buf);
		return "success";
	}

	private String randomCode() {
		String chs = "4567ABCDEFHJKLXYabcdrhknp";
		char[] code = new char[4];
		for (int i = 0; i < code.length; i++) {
			int index = (int) (Math.random() * chs.length());
			code[i] = chs.charAt(index);
		}
		return new String(code);
	}
	

}
