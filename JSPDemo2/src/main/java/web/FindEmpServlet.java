package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

/**
 * Servlet implementation class FindEmpServlet
 */
public class FindEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询所有的员工
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//转发给find_emp.jsp
		
		//将数据绑定到request
		request.setAttribute("emps", list);
		//转发到jsp
		//当前路径：/JSPDemo2/findEmp
		//目标路径：/JSPDemo/emps.jsp
		request.getRequestDispatcher("emps.jsp").forward(request, response);
		
		
	}

}
