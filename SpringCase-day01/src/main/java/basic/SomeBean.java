package basic;
/**
 * ����������һ��Some Beanʵ���������ṩ��ʼ�����������ٷ�����
 * @author Jesse
 *
 */
public class SomeBean {
	public SomeBean() {
		System.out.println("ʵ����SomeBean...");
	}
	public void init(){
		System.out.println("��ʼ��SomeBean...");
	}
	public void destroy(){
		System.out.println("����SomeBean...");
	}
}
