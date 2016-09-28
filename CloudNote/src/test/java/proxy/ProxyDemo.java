package proxy;

import java.lang.reflect.Proxy;


public class ProxyDemo {

	public static void main(String[] args){
		DemoBeanIf bean = new DemoBeanImpl();
		DemoBeanAspect aspect = new DemoBeanAspect(bean);
		//ʹ��java�ṩ�Ķ�̬����
		//���صĶ�����ʵ��DemoBeanIf�ӿڵ�һ��ʵ�������
		DemoBeanIf proxyBean =  (DemoBeanIf) Proxy.newProxyInstance(DemoBeanIf.class.getClassLoader(), new Class[]{DemoBeanIf.class}, aspect);
		
		proxyBean.hello();
	}

}
