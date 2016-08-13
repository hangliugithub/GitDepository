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
 * 这个类统一处理项目中所有的请求。
 * 
 * 路径规范：
 * 	查询员工：/findEmp.do
 *	增加员工：/addEmp.do
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取Servlet访问路径
		String path = req.getServletPath();
		//根据规范判断路径
		if("/findEmp.do".equals(path)){
			//查询员工
			findEmp(req,res);
		}else if("/addEmp.do".equals(path)){
			//增加员工
			addEmp(req,res);
		} else{
			throw new RuntimeException("没有这个页面！");
		}
	}

	private void addEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Emp e = new Emp();
		e.setEname(req.getParameter("ename"));
		e.setJob(req.getParameter("job"));
		e.setSal(Double.parseDouble(req.getParameter("sal")));
		EmpDao dao = new EmpDao();
		if(dao.add(e)){
			res.sendRedirect("findEmp.do");
		}else{
			System.out.println("添加失败！");
			res.getWriter().println("<script>alert('添加失败')</script>");
			res.sendRedirect("findEmp.do");
		}
	}

	private void findEmp(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		List<Emp> list = new EmpDao().findAll();
		req.setAttribute("emps", list);
		req.getRequestDispatcher("find_emp.jsp").forward(req, res);
		
	}

}
