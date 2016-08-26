package priv.jesse.netctoss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.jesse.netctoss.dao.AdminDAO;
import priv.jesse.netctoss.entity.Admin;
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name="adminDAO")
	private AdminDAO dao;
	
	public Admin checkLogin(String code, String pwd) {
		Admin admin = null;
		admin = dao.findByAdminCode(code);
		if(admin==null){
			//账号不存在,抛出引用异常
			throw new ApplicationException("账号不存在！|");
		}
		if(!admin.getPassword().equals(pwd)){
			//密码错误，抛出引用异常
			throw new ApplicationException("密码错误！");
		}
		//验证通过，返回admin对象
		return admin;
	}

}
