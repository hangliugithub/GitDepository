package priv.jesse.web;

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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class ImageAction {

	private InputStream image;
	
	private String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public InputStream getImage() {
		return image;
	}


	public void setImage(InputStream image) {
		this.image = image;
	}


	public ImageAction() {
	}

	
	public String execute(){
		byte[] buf;
		// 创建图片对象
		BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		// 填充背景为 灰色
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				img.setRGB(x, y, 0xEEEEEE);
			}
		}
		// 绘制500个麻点
		for (int i = 0; i < 1000; i++) {
			int x = (int) (Math.random() * 800);
			int y = (int) (Math.random() * 600);
			int rgb = (int) (Math.random() * 0xffffff);
			img.setRGB(x, y, rgb);
		}
		// 获取画笔对象
		Graphics2D g = img.createGraphics();
		// 随机色
		int rgb = (int) (Math.random() * 0xffffff);
		g.setColor(new Color(rgb));
		// 设置字体大小
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		g.setFont(font);

		// 抗锯齿，平滑绘制
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		// 生成验证码
		String code = "Hello World";
		// 将验证码保存到session
		//request.getSession().setAttribute("code", code);
		// 绘制验证码
		int x = (int) (Math.random() * 200);
		int y = (int) (Math.random() * 550);
		g.drawString(code, x, y);
		// 绘制5条乱线
//		for (int i = 0; i < 5; i++) {
//			int x1 = (int) (Math.random() * 80);
//			int x2 = (int) (Math.random() * 80);
//			int y1 = (int) (Math.random() * 30);
//			int y2 = (int) (Math.random() * 30);
//			rgb = (int) (Math.random() * 0xffffff);
//			g.setColor(new Color(rgb));
//			g.drawLine(x1, y1, x2, y2);
//		}
		// 利用ByteArrayOutputStream获取png编码数据
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "png", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buf = out.toByteArray();
		image = new ByteArrayInputStream(buf);
		return "success";
	}
	
	
	public String card() throws IOException{
		//读取图片
		//再包中读取文件的做法
		InputStream in = getClass().getClassLoader().getResourceAsStream("1.jpg");
		BufferedImage img = ImageIO.read(in);
		in.close();
		Graphics2D g = img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setColor(new Color(255,128,64));
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		g.drawString(name+" 我在这里等你...",10,1060);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", out);
		byte[] data = out.toByteArray();
		out.close();
		image = new ByteArrayInputStream(data);
		return "success";
	}
}
