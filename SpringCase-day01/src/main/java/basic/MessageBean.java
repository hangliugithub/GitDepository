package basic;
/**
 * ��ʾ����������صļ���������ʹ��
 * 
 * @author Jesse
 *
 */
public class MessageBean {
	
	public MessageBean() {
		System.out.println("MessegeBean's MessegeBean()");
	}
	
	/**
	 * ��ʼ������
	 */
	public void init(){
		System.out.println("init...");
	}
	
	public void sendMessage(){
		System.out.println("SendMesage...");
	}
	
	/**
	 * ���ٷ���
	 */
	public void destroy(){
		System.out.println("destroy...");
	}
}
