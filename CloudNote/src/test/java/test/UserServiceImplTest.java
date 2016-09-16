package test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.service.UserService;
import priv.jesse.cloudnote.util.Md5;

public class UserServiceImplTest {
	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");

	}

	@Test
	public void testLogin() {
		UserService service = (UserService) ac.getBean("userService");
		User user = service.login("Catalina", "123");
		System.out.println(user);

		user = service.login("Catalina", "123456");
	}

	@Test
	public void testMd5() {
		try {
			// 消息摘要
			// MessageDigest封装了复制的消息摘要算法,"MD5"是算法名称
			MessageDigest md = MessageDigest.getInstance("MD5");
			/*
			 * 下面是处理大文件数据的摘要方法
			 */
			// //update 提交数据，如果多次调用的话，就是对一堆数据进行摘要
			// md.update(input);
			// //计算摘要
			// byte[] digest = md.digest();
			
			String pwd = "123456";
			byte[] data = pwd.getBytes("utf-8");
			byte[] md5 = md.digest(data);
			System.out.println("md5:"+new String(md5));
//			String md5str = new String(md5,"utf-8");
//			System.out.println(md5str);
//			for(byte b : md5){
//				System.out.println(b);
//			}
			//转换成16进制
			String hex = Hex.encodeHexString(md5);
			System.out.println(hex);
			//base64
			String base64 = Base64.encodeBase64String(md5);
			System.out.println(base64);
			System.out.println("md5:"+new String(Base64.decodeBase64(base64)));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		System.out.println(Md5.saltMd5("123"));
	}
	
	@Test
	public void testRegist(){
		UserService service = (UserService) ac.getBean("userService");
		User user = service.regist("Tom", "123456", "TomCat");
		//User user = service.regist("Tom", "123", "");
		System.out.println(user);
	}

}
