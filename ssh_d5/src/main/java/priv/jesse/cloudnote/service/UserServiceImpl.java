package priv.jesse.cloudnote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.jesse.cloudnote.dao.UserDAO;
import priv.jesse.cloudnote.entity.User;
import priv.jesse.cloudnote.util.Md5;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = -6398539750757226730L;

	@Autowired
	private UserDAO userDAO;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly=true)
	public User login(String name, String password) throws NameOrPasswordException {
		// 入口参数检查
		if (name == null || name.trim().isEmpty()) {
			throw new NameOrPasswordException("用户名不能为空！");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new NameOrPasswordException("密码不能为空！");
		}
		// 从持久层查询用户信息
		User user = userDAO.findUserByName(name);
		if (user == null) {
			throw new NameOrPasswordException("用户不存在！");
		}
		if (user.getPassword().equals(Md5.saltMd5(password)) || user.getPassword().equals(Md5.md5(password))) {
			return user;
		}
		throw new NameOrPasswordException("密码错误！");
	}

	@Transactional
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

	@Transactional(readOnly=true)
	public boolean checkPassword(String userId, String password) throws NameOrPasswordException {
		// 入口参数检查
		if (userId == null || userId.trim().isEmpty()) {
			throw new NameOrPasswordException("用户ID不能为空！");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new NameOrPasswordException("密码不能为空！");
		}
		
		User user = userDAO.findUserById(userId);
		if(user==null){
			throw new NameOrPasswordException("用户不存在！");
		}
		if(!user.getPassword().equals(Md5.saltMd5(password))){
			return false;
		}
		return true;
	}

	@Transactional
	public User changePassword(String userId, String password, String newPassword) throws ServiceException {
		//System.out.println(userId+","+password);
		// 入口参数检查
		if (userId == null || userId.trim().isEmpty()) {
			throw new NameOrPasswordException("用户ID不能为空！");
		}
		User user = userDAO.findUserById(userId);
		if(user==null){
			throw new NameOrPasswordException("用户不存在！");
		}
		if (!user.getPassword().equals(Md5.saltMd5(password))) {
			throw new NameOrPasswordException("原始密码不正确！");
		}
		if(!newPassword.matches("^.{3,10}$")){
			throw new NameOrPasswordException("新密码不合格！");
		}
		user.setPassword(Md5.saltMd5(newPassword));
		userDAO.updateUser(user);
		return user;
	}

	
	
}
