package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * �Զ����ǩ<t:sysdate format="">
 * @author Jesse
 *
 */
public class SysdateTag extends SimpleTagSupport {
	/**
	 * tomcat�ڷ���JSPʱ�����ڶ�ȡ���˱�ǩʱ���ͻ���ô˱�ǩ�ಢ���ô˷���
	 */
	//ʱ���ʽ����Ĭ��ֵ
	//Ҳ��ͨ��set�����޸�
	//tomcat����ʵ�����˶���󣬵���doTag()����֮ǰ�޸Ĵ�ֵ
	private String format = "yyyy-MM-dd HH:mm:ss";
	@Override
	public void doTag() throws JspException, IOException {
		//������������ǰϵͳʱ��
		Date date = new Date();
		//��ʽ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String now = sdf.format(date);
		//��ʱ������������
		//getJspContext()������������JspContext���ײ�ʵ��ʱ���ص�����������(PageContext)����
		//�˴�����ǿת��PageContext��Ϳ��Ի������8�����ö�����
		PageContext ctx =  (PageContext)getJspContext();
		JspWriter out = ctx.getOut();
		out.println(now);
		
		//�˴�ǧ��Ҫ�ر�������ΪJSP�Լ�JSP�����������ǩҲҪʹ�ø���
		//out.close();
	}
	
	public void setFormat(String format){
		this.format = format;
	}
	
	
}
