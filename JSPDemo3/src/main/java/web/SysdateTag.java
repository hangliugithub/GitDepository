package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 自定义标签<t:sysdate format="">
 * @author Jesse
 *
 */
public class SysdateTag extends SimpleTagSupport {
	/**
	 * tomcat在翻译JSP时当它在读取到此标签时，就会调用此标签类并调用此方法
	 */
	//时间格式，有默认值
	//也可通过set方法修改
	//tomcat会在实例化此对象后，调用doTag()方法之前修改此值
	private String format = "yyyy-MM-dd HH:mm:ss";
	@Override
	public void doTag() throws JspException, IOException {
		//创建服务器当前系统时间
		Date date = new Date();
		//格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String now = sdf.format(date);
		//将时间输出给浏览器
		//getJspContext()方法声明返回JspContext，底层实现时返回的是它的子类(PageContext)对象
		//此处将其强转回PageContext后就可以获得其他8个内置对象了
		PageContext ctx =  (PageContext)getJspContext();
		JspWriter out = ctx.getOut();
		out.println(now);
		
		//此处千万不要关闭流，因为JSP以及JSP上面的其他标签也要使用该流
		//out.close();
	}
	
	public void setFormat(String format){
		this.format = format;
	}
	
	
}
