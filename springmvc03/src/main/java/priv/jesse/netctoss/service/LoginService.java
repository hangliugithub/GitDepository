package priv.jesse.netctoss.service;

import priv.jesse.netctoss.entity.Admin;

public interface LoginService {
	public Admin checkLogin(String code, String pwd);
}
