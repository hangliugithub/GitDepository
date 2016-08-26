package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * ��ʾCookieʹ�ã�ģ�⿪����¼���ܣ��ڵ�¼ʱ����Cookie������ҳ�������˺ţ��ڽ�Cookie���͸������
	 * ��������Զ�����Cookie
	 * �˷����൱��netctoss��Ŀ�е�login()
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//����ҳ�洫����˺�
		String code = req.getParameter("code");
		//���˺Ŵ���Cookie
		//Cookie��ֻ�ܱ���һ�Լ�ֵ�ԣ�����key��value���붼��String
		Cookie c1 = new Cookie("code", code);
		
		//����cookie����Ч·��
		c1.setPath("/JSPDemo4_Cookie");
		
		//����Cookie������ʱ��
		c1.setMaxAge(100);
		
		//��cookie���뵽response�ڣ�����Ӧʱ�ɷ������Զ����͸������
		res.addCookie(c1);
		
		//�ٴ���һ��Cookie�����ģ�Ĭ��cookie���ܴ����ģ����򱨴�
		//��Ҫ������ת��(ASCII)����ܱ���
		Cookie c2 = new Cookie("city",URLEncoder.encode("����", "utf-8"));
		res.addCookie(c2);
	}

}
