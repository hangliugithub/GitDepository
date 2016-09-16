package priv.jesse.cloudnote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User login(String name, String password) throws NameOrPasswordException {
		// ��ڲ������
		if (name == null || name.trim().isEmpty()) {
			throw new NameOrPasswordException("�û�������Ϊ�գ�");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new NameOrPasswordException("���벻��Ϊ�գ�");
		}
		// �ӳ־ò��ѯ�û���Ϣ
		User user = userDAO.findUserByName(name);
		if (user == null) {
			throw new NameOrPasswordException("�û������ڣ�");
		}
		if (user.getPassword().equals(Md5.saltMd5(password)) || user.getPassword().equals(Md5.md5(password))) {
			return user;
		}
		throw new NameOrPasswordException("�������");
	}

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

	
	
}
