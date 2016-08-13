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
		//��ȡ�ύ����������
		Emp e = new Emp();
		req.setCharacterEncoding("utf-8");
		e.setEname(req.getParameter("ename"));
		e.setJob(req.getParameter("job"));
		e.setSal(Double.parseDouble(req.getParameter("sal")));
		EmpDao dao = new EmpDao();
		
		//���ͻ�����Ӧ
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		if(dao.add(e)){
			//res.sendRedirect("findEmp");
			out.println("<script>alert('��ӳɹ�');</script>");
			
		}else{
			//res.sendRedirect("findEmp");
			out.println("<script>alert('���ʧ�ܣ�');</script>");
			
		}
		//�ض����������ת��ʽ������ѯ
		//��ǰ��/EmpManager/add
		//Ŀ�꣺/EmpManager/findEmp
		//res.sendRedirect("findEmp");
		

		out.close();
	}
	

}
