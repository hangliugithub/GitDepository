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
		//入口参数检查
		if(name == null || name.trim().isEmpty()){
			throw new ServiceException("用户名不能为空！");
		}
		if(!name.matches(rule)){
			throw new ServiceException("用户名不合格！");
		}
		if(password ==null || password.trim().isEmpty()){
			throw new ServiceException("密码不能为空！");
		}
		if(!password.matches(rule)){
			throw new ServiceException("密码不合格！");
		}
		rule = "^.{3,10}$";
		if(nick == null || nick.trim().isEmpty()){
			throw new ServiceException("昵称不能为空");
		}
		if(!nick.matches(rule)){
			throw new ServiceException("昵称不合格！");
		}
		
		User user = userDAO.findUserByName(name);
		if(user!=null){
			throw new UserExistException("用户已存在！");
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
//				throw new ServiceException("用户已存在！");
//			}
//			String id = UUID.randomUUID().toString();
			String password = randomPwd(6);
			User user = regist(name, password, name);
//			User user = new User(id, name, Md5.saltMd5(password), "", name);
//			userDAO.saveUser(user);
			list.add(user);
		}
		//spring的事务只绑定到当前线程
		//业务层开启了子线程中的数据操作不参与当前事务，子线程事务单独处理
		new Thread(){
			public void run() {
				//userDAO.saveUser(new User());
				//子线程中调用有事务管理的方法即可
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
