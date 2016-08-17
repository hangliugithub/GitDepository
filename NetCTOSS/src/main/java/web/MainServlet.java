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
		//��ȡ����·��
		String path = req.getServletPath();
		//���ݹ淶�ж�·��
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
			throw new RuntimeException("û�����ҳ�棡");
		}
	}
	
	/**
	 * ������֤��
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//������֤�뼰ͼƬ
		Object[] objs = ImageUtil.createImage();
		//����֤�����session
		HttpSession session = req.getSession();
		session.setAttribute("valicode", (String)objs[0]);
		
		//��ͼƬ����������
		//contentType�淶��Servers/web.xml����
		res.setContentType("image/png");
		
		//�������tomcat�Զ���������ָ�������
		BufferedImage img = (BufferedImage)objs[1];
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}

	/**
	 * �����¼����
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
			//throw new RuntimeException("�û�������");
			req.setAttribute("error", "�û�������");
			//req.setAttribute("code", code);
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			
		}else if(!admin.getPassword().equals(password)){
			//throw new RuntimeException("�������");
			req.setAttribute("error", "�������");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else if(!relValicode.equalsIgnoreCase(valicode)){
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else{
			//��cookie�����¼���˺�
			//Cookie cookie = new Cookie("adminCode",code);
			//���ģ��������ģ����ô���
			//����ʱ�䣺ÿ�η��ʸ���Ŀ�������ȵ�¼�����Բ��ش���Ӳ��
			//��Ч·������ǰ·��ʱ /NetCTOSS/login.do,����cookie����Ч·����NetCTOSS�����в��ø���
			//res.addCookie(cookie);
			
			//Ҳ���Խ��˺Ŵ���session��
			HttpSession session  = req.getSession();
			session.setAttribute("adminCode", code);
			
			res.sendRedirect("toIndex.do");
		}
		
	}

	/**
	 * ����ҳ
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
	 * �򿪵�½ҳ��
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
	 * ���� ��ʾ�ʷ����������
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
	 * ���� �޸�Cost������
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
	 * ������cost������
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
	 * ������ת�����Costҳ�������
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
	 * ��ѯ�ʷ�
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		req.setAttribute("costs", list);
		//ת����jsp
		//��ǰ��/NetCTOSS/findCost.do
		//Ŀ�ģ�/NetCTOSS/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
}
