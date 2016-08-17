package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.CostDao;
import entity.Admin;
import entity.Cost;
import utils.ImageUtil;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取访问路径
		String path = req.getServletPath();
		//根据规范判断路径
		if("/findCost.do".equals(path)){
			findCost(req,res);
		}else if("/toAddCost.do".equals(path)){
			toAddCost(req,res);
		}else if("/saveCost.do".equals(path)){
			saveCost(req,res);
		}else if("/delCost.do".equals(path)){
			delCost(req,res);
		}else if("/toModifyCost.do".equals(path)){
			toModifyCost(req,res);
		}else if("/modifyCost.do".equals(path)){
			modifyCost(req,res);
		}else if("/detialCost.do".equals(path)){
			detialCost(req,res);
		}else if("/toLogin.do".equals(path)){
			toLogin(req,res);
		}else if("/login.do".equals(path)){
			login(req,res);
		}else if("/toIndex.do".equals(path)){
			toIndex(req,res);
		}else if("/createImg.do".equals(path)){
			createImg(req,res);
		}else{
			throw new RuntimeException("没有这个页面！");
		}
	}
	
	/**
	 * 生成验证码
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//生成验证码及图片
		Object[] objs = ImageUtil.createImage();
		//将验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("valicode", (String)objs[0]);
		
		//将图片输出给浏览器
		//contentType规范从Servers/web.xml中找
		res.setContentType("image/png");
		
		//输出流由tomcat自动创建，它指向浏览器
		BufferedImage img = (BufferedImage)objs[1];
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}

	/**
	 * 处理登录请求
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		String password = req.getParameter("password");
		String valicode = req.getParameter("valicode");
		String relValicode = (String)req.getSession().getAttribute("valicode");
		Admin admin = new AdminDao().findByCode(code);
		if(admin==null){
			//throw new RuntimeException("用户名错误！");
			req.setAttribute("error", "用户名错误！");
			//req.setAttribute("code", code);
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			
		}else if(!admin.getPassword().equals(password)){
			//throw new RuntimeException("密码错误！");
			req.setAttribute("error", "密码错误！");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else if(!relValicode.equalsIgnoreCase(valicode)){
			req.setAttribute("error", "验证码错误！");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else{
			//用cookie保存登录的账号
			//Cookie cookie = new Cookie("adminCode",code);
			//中文：不是中文，不用处理
			//生存时间：每次访问该项目，都得先登录，所以不必存入硬盘
			//生效路径：当前路径时 /NetCTOSS/login.do,所有cookie的有效路径是NetCTOSS，所有不用改了
			//res.addCookie(cookie);
			
			//也可以将账号存入session中
			HttpSession session  = req.getSession();
			session.setAttribute("adminCode", code);
			
			res.sendRedirect("toIndex.do");
		}
		
	}

	/**
	 * 打开主页
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}
	/**
	 * 打开登陆页面
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		res.setHeader("Pragma","no-cache");  
	    res.setHeader("Cache-Control","no-catch");  
	    res.setDateHeader("Expires",0); 
	}

	/**
	 * 处理 显示资费详情的请求
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void detialCost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String costId = req.getParameter("id");
		Cost c = new CostDao().findById(costId);
		req.setAttribute("cost", c);
		req.getRequestDispatcher("WEB-INF/cost/detial.jsp").forward(req, res);
		
	}

	/**
	 * 处理 修改Cost的请求
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	private void modifyCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String costId = req.getParameter("costId");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		Cost c = new Cost();
		c.setCostId(Integer.parseInt(costId));
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration !=null && baseDuration!=""){
			c.setBaseDuration(Integer.parseInt(baseDuration));
		}
//		else{
//			c.setBaseDuration(0);
//		}
		if(baseCost !=null && baseCost!=""){
			c.setBaseCost(Double.parseDouble(baseCost));
		}
//		else{
//			c.setBaseCost(0.0);
//		}
		if(unitCost!=null && unitCost!=""){
			c.setUnitCost(Double.parseDouble(unitCost));
		}
//		else{
//			c.setUnitCost(0.0);
//		}
		c.setDescr(descr);
		CostDao dao = new CostDao();
		dao.modifyCost(c);
		res.sendRedirect("findCost.do");
	}
	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	private void toModifyCost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("costId");
		System.out.println(id);
		CostDao dao = new CostDao();
		Cost c = dao.findById(id);
		req.setAttribute("cost", c);
		req.getRequestDispatcher("WEB-INF/cost/modify.jsp").forward(req, res);
	}

	private void delCost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String costId = req.getParameter("costId");
		//System.out.println(costId);
		CostDao dao = new CostDao();
		boolean deleted = dao.delCostById(costId);
		if(deleted){
			//res.sendRedirect("findCost.do");
			req.setAttribute("deleted", deleted);
			req.getRequestDispatcher("findCost.do").forward(req, res);
		}
		
	}

	/**
	 * 处理保存cost的请求
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void saveCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		Cost c = new Cost();
		c.setName(req.getParameter("name"));
		c.setCostType(req.getParameter("costType"));
		if(req.getParameter("baseDuration")!=null && !"".equals(req.getParameter("baseDuration")))
			//System.out.println(req.getParameter("baseDutation"));
			c.setBaseDuration(new Integer(req.getParameter("baseDutation")));
		//else
		//	c.setBaseDuration(0);
		if(req.getParameter("baseCost")!=null && !"".equals(req.getParameter("baseCost")))
			c.setBaseCost(Double.parseDouble(req.getParameter("baseCost")));
		//else
		//	c.setBaseCost(0.0);
		if(req.getParameter("unitiCost")!=null && !"".equals(req.getParameter("unitiCost")))
			c.setUnitCost(Double.parseDouble(req.getParameter("unitiCost")));
		//else
			c.setUnitCost(0.0);
		c.setDescr(req.getParameter("descr"));
		CostDao dao = new CostDao();
		dao.saveCost(c);
		res.sendRedirect("findCost.do");
	}
	
	/**
	 * 处理跳转到添加Cost页面的请求
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	/**
	 * 查询资费
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		req.setAttribute("costs", list);
		//转发到jsp
		//当前：/NetCTOSS/findCost.do
		//目的：/NetCTOSS/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
}
