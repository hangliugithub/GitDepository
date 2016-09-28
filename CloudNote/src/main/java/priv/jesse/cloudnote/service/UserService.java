package priv.jesse.cloudnote.service;

import java.io.Serializable;

import priv.jesse.cloudnote.entity.User;

public interface UserService extends Serializable{
	/**
	 * 登录方法
	 * @param name 用户名
	 * @param password 密码
	 * @return 登录成功时返回用户信息
	 * @throws NameOrPasswordException 用户名或密码错误
	 * 			用户名或密码为空
	 */
	User login(String name,String password) throws NameOrPasswordException;
	/**
	 * 注册
	 * @param name
	 * @param password
	 * @param nick 昵称
	 * @return 返回注册成功的用户信息
	 * @throws UserExistException 用户已存在
	 */
	User regist(String name, String password, String nick) throws UserExistException;
	
	boolean checkPassword(String userId,String password) throws NameOrPasswordException;
	
	User changePassword(String userId,String password,String newPassword) throws NameOrPasswordException,ServiceException;
	
}
