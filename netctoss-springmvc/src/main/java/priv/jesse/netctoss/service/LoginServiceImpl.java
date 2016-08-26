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
			//�˺Ų�����,�׳������쳣
			throw new ApplicationException("�˺Ų����ڣ�|");
		}
		if(!admin.getPassword().equals(pwd)){
			//��������׳������쳣
			throw new ApplicationException("�������");
		}
		//��֤ͨ��������admin����
		return admin;
	}

}
