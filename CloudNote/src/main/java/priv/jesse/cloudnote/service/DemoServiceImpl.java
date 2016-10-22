package priv.jesse.cloudnote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.jesse.cloudnote.dao.UserDAO;
import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.util.Md5;

//@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Resource
	private UserDAO userDAO;
	
	public DemoServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public User regist(String name, String password, String nick) throws UserExistException {
		String rule = "^\\w{3,10}";
		//��ڲ������
		if(name == null || name.trim().isEmpty()){
			throw new ServiceException("�û�������Ϊ�գ�");
		}
		if(!name.matches(rule)){
			throw new ServiceException("�û������ϸ�");
		}
		if(password ==null || password.trim().isEmpty()){
			throw new ServiceException("���벻��Ϊ�գ�");
		}
		if(!password.matches(rule)){
			throw new ServiceException("���벻�ϸ�");
		}
		rule = "^.{3,10}$";
		if(nick == null || nick.trim().isEmpty()){
			throw new ServiceException("�ǳƲ���Ϊ��");
		}
		if(!nick.matches(rule)){
			throw new ServiceException("�ǳƲ��ϸ�");
		}
		
		User user = userDAO.findUserByName(name);
		if(user!=null){
			throw new UserExistException("�û��Ѵ��ڣ�");
		}
		String id = UUID.randomUUID().toString();
		String salPwd = Md5.saltMd5(password);
		user = new User(id, name, salPwd, "", nick);
		userDAO.saveUser(user);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> batchRegist(String...userName){
		
		List<User> list = new ArrayList<User>();
		for(String name:userName){
//			User user = userDAO.findUserByName(name);
//			if(user!=null){
//				throw new ServiceException("�û��Ѵ��ڣ�");
//			}
//			String id = UUID.randomUUID().toString();
			String password = randomPwd(6);
			User user = regist(name, password, name);
//			User user = new User(id, name, Md5.saltMd5(password), "", name);
//			userDAO.saveUser(user);
			list.add(user);
		}
		//spring������ֻ�󶨵���ǰ�߳�
		//ҵ��㿪�������߳��е����ݲ��������뵱ǰ�������߳����񵥶�����
		new Thread(){
			public void run() {
				//userDAO.saveUser(new User());
				//���߳��е������������ķ�������
				//batchRegist("andy");
			};
		}.start();
		
		return list;
	}
	private String randomPwd(int i) {
		char[] pwd = new char[i];
		String str = "1234567890abcdefghijk";
		for(int n=0;n<pwd.length;n++){
			int index = (int) (Math.random()*str.length());
			pwd[n]=str.charAt(index);
		}
		return new String(pwd);
	}

}
