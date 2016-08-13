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
 * �����ͳһ������Ŀ�����е�����
 * 
 * ·���淶��
 * 	��ѯԱ����/findEmp.do
 *	����Ա����/addEmp.do
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡServlet����·��
		String path = req.getServletPath();
		//���ݹ淶�ж�·��
		if("/findEmp.do".equals(path)){
			//��ѯԱ��
			findEmp(req,res);
		}else if("/addEmp.do".equals(path)){
			//����Ա��
			addEmp(req,res);
		} else{
			throw new RuntimeException("û�����ҳ�棡");
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
			System.out.println("���ʧ�ܣ�");
			res.getWriter().println("<script>alert('���ʧ��')</script>");
			res.sendRedirect("findEmp.do");
		}
	}

	private void findEmp(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		List<Emp> list = new EmpDao().findAll();
		req.setAttribute("emps", list);
		req.getRequestDispatcher("find_emp.jsp").forward(req, res);
		
	}

}
