package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if("/toRegister.do".equals(path)){
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}
		if("/check_name.do".equals(path)){
			checkName(request,response);
		}
		if("/register.do".equals(path)){
			register(request,response);
		}
		if("/setCity.do".equals(path)){
			setCity(request,response);
		}
	}

	private void setCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String province = request.getParameter("province");
		response.setContentType("text");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if("jx".equals(province)){
			out.println("南昌=nc,吉安=ja,赣州=gz");
		}else if("zj".equals(province)){
			out.println("杭州=hz,嘉兴=jx,宁波=nb");
		}
		out.close();
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
//		Enumeration<String> en = request.getParameterNames();
//		if(en.hasMoreElements()){
//			System.out.println(en.nextElement());
//		}
		String name = request.getParameter("uname");
		System.out.println(name);
		response.setContentType("text");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("小明".equals(name)){
			out.println("该用户已存在！");
		}else{
			out.println("可以使用！");
		}
		out.close();
		
	}

}
