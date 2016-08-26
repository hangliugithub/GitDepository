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
		//模拟查询学生
		Student stu = new Student();
		stu.setName("张三");
		stu.setAge(23);
		stu.setSex("M");
		stu.setInterests(new String[]{"篮球","足球","羽毛球"});
		
		Course c = new Course();
		c.setId(1);
		c.setCourseName("Java");
		c.setDays(80);
		
		stu.setCourse(c);
		
		//转发到JSP
		request.setAttribute("student", stu);
		//当前路径：JSPDemo3/find
		//目标路径：JSPDemo3/find.jsp
		request.getRequestDispatcher("find.jsp").forward(request, response);
	}

}
