package priv.jesse.cloudnote.service;

import java.util.List;

import priv.jesse.cloudnote.entity.User;

public interface DemoService {
	User regist(String name, String password, String nick) throws UserExistException;
	
	/*
	 * String... ����String[]
	 * ��������滻ΪString[] new String[]{userId...}
	 * ��Ϊ�䳤����������������һ������λ����
	 */
	List<User> batchRegist(String...userName);
}
