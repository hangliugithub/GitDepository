package priv.jesse.cloudnote.service;

import java.util.List;

import priv.jesse.cloudnote.entity.User;

public interface DemoService {
	User regist(String name, String password, String nick) throws UserExistException;
	
	/*
	 * String... 就是String[]
	 * 编译擦除替换为String[] new String[]{userId...}
	 * 成为变长参数，必须放在最后一个参数位置上
	 */
	List<User> batchRegist(String...userName);
}
