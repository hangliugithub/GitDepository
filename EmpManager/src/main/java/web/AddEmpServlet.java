package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

public class AddEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//读取提交过来的数据
		Emp e = new Emp();
		req.setCharacterEncoding("utf-8");
		e.setEname(req.getParameter("ename"));
		e.setJob(req.getParameter("job"));
		e.setSal(Double.parseDouble(req.getParameter("sal")));
		EmpDao dao = new EmpDao();
		
		//给客户端响应
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		if(dao.add(e)){
			//res.sendRedirect("findEmp");
			out.println("<script>alert('添加成功');</script>");
			
		}else{
			//res.sendRedirect("findEmp");
			out.println("<script>alert('添加失败！');</script>");
			
		}
		//重定向（特殊的跳转方式）到查询
		//当前：/EmpManager/add
		//目标：/EmpManager/findEmp
		//res.sendRedirect("findEmp");
		

		out.close();
	}
	

}
