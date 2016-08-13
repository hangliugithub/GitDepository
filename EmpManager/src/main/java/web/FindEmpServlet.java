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
		//查询所有员工
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//发送响应信息
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<div style='margin:30px auto;width:500px;'>");
		out.println("<input type='button' value='增加' onclick='location.href=\"add_emp.html\"'><br><br>");
		out.println("<table border='1px' style='border:1px solid #888;border-collapse:collapse;width:500px'>");
		out.println("<tr>");
		out.println("<th>");
		out.println("编号");
		out.println("</th>");
		out.println("<th>");
		out.println("姓名");
		out.println("</th>");
		out.println("<th>");
		out.println("职位");
		out.println("</th>");
		out.println("<th>");
		out.println("月薪");
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
