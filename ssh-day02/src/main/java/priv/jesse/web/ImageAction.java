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
		// ����ͼƬ����
		BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		// ��䱳��Ϊ ��ɫ
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				img.setRGB(x, y, 0xEEEEEE);
			}
		}
		// ����500�����
		for (int i = 0; i < 1000; i++) {
			int x = (int) (Math.random() * 800);
			int y = (int) (Math.random() * 600);
			int rgb = (int) (Math.random() * 0xffffff);
			img.setRGB(x, y, rgb);
		}
		// ��ȡ���ʶ���
		Graphics2D g = img.createGraphics();
		// ���ɫ
		int rgb = (int) (Math.random() * 0xffffff);
		g.setColor(new Color(rgb));
		// ���������С
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		g.setFont(font);

		// ����ݣ�ƽ������
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		// ������֤��
		String code = "Hello World";
		// ����֤�뱣�浽session
		//request.getSession().setAttribute("code", code);
		// ������֤��
		int x = (int) (Math.random() * 200);
		int y = (int) (Math.random() * 550);
		g.drawString(code, x, y);
		// ����5������
//		for (int i = 0; i < 5; i++) {
//			int x1 = (int) (Math.random() * 80);
//			int x2 = (int) (Math.random() * 80);
//			int y1 = (int) (Math.random() * 30);
//			int y2 = (int) (Math.random() * 30);
//			rgb = (int) (Math.random() * 0xffffff);
//			g.setColor(new Color(rgb));
//			g.drawLine(x1, y1, x2, y2);
//		}
		// ����ByteArrayOutputStream��ȡpng��������
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
		//��ȡͼƬ
		//�ٰ��ж�ȡ�ļ�������
		InputStream in = getClass().getClassLoader().getResourceAsStream("1.jpg");
		BufferedImage img = ImageIO.read(in);
		in.close();
		Graphics2D g = img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setColor(new Color(255,128,64));
		g.setFont(new Font("΢���ź�", Font.BOLD, 40));
		g.drawString(name+" �����������...",10,1060);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", out);
		byte[] data = out.toByteArray();
		out.close();
		image = new ByteArrayInputStream(data);
		return "success";
	}
}
