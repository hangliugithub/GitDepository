package basic;
/**
 * 演示生命周期相关的几个方法的使用
 * 
 * @author Jesse
 *
 */
public class MessageBean {
	
	public MessageBean() {
		System.out.println("MessegeBean's MessegeBean()");
	}
	
	/**
	 * 初始化方法
	 */
	public void init(){
		System.out.println("init...");
	}
	
	public void sendMessage(){
		System.out.println("SendMesage...");
	}
	
	/**
	 * 销毁方法
	 */
	public void destroy(){
		System.out.println("destroy...");
	}
}
