package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Course;
import entity.Student;

/**
 * Servlet implementation class FindServlet
 */
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ģ���ѯѧ��
		Student stu = new Student();
		stu.setName("����");
		stu.setAge(23);
		stu.setSex("M");
		stu.setInterests(new String[]{"����","����","��ë��"});
		
		Course c = new Course();
		c.setId(1);
		c.setCourseName("Java");
		c.setDays(80);
		
		stu.setCourse(c);
		
		//ת����JSP
		request.setAttribute("student", stu);
		//��ǰ·����JSPDemo3/find
		//Ŀ��·����JSPDemo3/find.jsp
		request.getRequestDispatcher("find.jsp").forward(request, response);
	}

}
