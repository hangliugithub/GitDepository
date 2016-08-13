package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

public class FindEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ѯ����Ա��
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//������Ӧ��Ϣ
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<div style='margin:30px auto;width:500px;'>");
		out.println("<input type='button' value='����' onclick='location.href=\"add_emp.html\"'><br><br>");
		out.println("<table border='1px' style='border:1px solid #888;border-collapse:collapse;width:500px'>");
		out.println("<tr>");
		out.println("<th>");
		out.println("���");
		out.println("</th>");
		out.println("<th>");
		out.println("����");
		out.println("</th>");
		out.println("<th>");
		out.println("ְλ");
		out.println("</th>");
		out.println("<th>");
		out.println("��н");
		out.println("</th>");
		out.println("</tr>");
		for(Emp e:list){
			out.println("<tr>");
			out.println("<td>");
			out.println(e.getEmpno());
			out.println("</td>");
			out.println("<td>");
			out.println(e.getEname());
			out.println("</td>");
			out.println("<td>");
			out.println(e.getJob());
			out.println("</td>");
			out.println("<td>");
			out.println(e.getSal());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("<table>");
		out.println("</div>");
		out.close();
		
	}
	
	

}
