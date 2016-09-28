package priv.jesse.cloudnote.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Md5 {

	public Md5() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 将一个String字符串进行摘要计算
	 * @param str 返回摘要后生成byte[] 数组转换成Base64的字符串
	 * @return
	 */
	public static String md5(String str){
		try {
			byte[] data = str.getBytes("utf-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			byte[] md5 = md.digest();
			String code = Base64.encodeBase64String(md5);
			return code;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 加盐版Md5摘要
	 * @param str
	 * @return
	 */
	public static String saltMd5(String str){
		
		String salt = "hello java";
		
		try {
			byte[] data = str.getBytes("utf-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			md.update(salt.getBytes("utf-8"));
			byte[] md5 = md.digest();
			String code = Base64.encodeBase64String(md5);
			return code;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
