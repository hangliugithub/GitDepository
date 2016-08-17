package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.��ȡ��ǰ������ʱ�䣨Tomcat�����еļ������
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(date);
		//2.��ʱ��ƴ����ҳ������������
		//�������������������ݵ�����
		res.setContentType("text/html");
		//��ȡ�������ʹ��������������
		PrintWriter out = res.getWriter();
		//��̬ƴHTML:����Ӧ��һ��һ�е�ƴ��HTML���˴��ͼ�ƴ��body�е����ݼ���
		out.println("<P>"+now+"</p>");
		//�ر������
		out.close();
	}

}





















