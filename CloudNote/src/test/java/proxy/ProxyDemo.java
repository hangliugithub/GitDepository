package proxy;

import java.lang.reflect.Proxy;


public class ProxyDemo {

	public static void main(String[] args){
		DemoBeanIf bean = new DemoBeanImpl();
		DemoBeanAspect aspect = new DemoBeanAspect(bean);
		//使用java提供的动态代理
		//返回的对象是实现DemoBeanIf接口的一个实现类对象
		DemoBeanIf proxyBean =  (DemoBeanIf) Proxy.newProxyInstance(DemoBeanIf.class.getClassLoader(), new Class[]{DemoBeanIf.class}, aspect);
		
		proxyBean.hello();
	}

}
