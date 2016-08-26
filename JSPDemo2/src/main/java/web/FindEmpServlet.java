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
		//��ѯ���е�Ա��
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//ת����find_emp.jsp
		
		//�����ݰ󶨵�request
		request.setAttribute("emps", list);
		//ת����jsp
		//��ǰ·����/JSPDemo2/findEmp
		//Ŀ��·����/JSPDemo/emps.jsp
		request.getRequestDispatcher("emps.jsp").forward(request, response);
		
		
	}

}
