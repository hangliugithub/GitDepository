package priv.jesse.web;

import java.util.ArrayList;
import java.util.List;

/**
 * struts2 ��������������֧���������
 * һ����Ʒ�����execute
 * ����ֵ��String ��Ӧ������������ �磺success error
 * �������ļ��н�����ӳ�䵽��ͼ��
 * success -> /WEB-INF/ok.jsp
 * @author Jesse
 *
 */
public class HelloAction {

	private String message;
	
	private List<String> names;
	
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String execute(){
		System.out.println(Thread.currentThread().getName());
		message = "hello world";
		names = new ArrayList<String>();
		names.add("Catalina");
		names.add("Tom");
		names.add("Andy");
		System.out.println("hello world");
		return "success";
	}

}
