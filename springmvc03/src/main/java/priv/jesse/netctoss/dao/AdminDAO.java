package priv.jesse.netctoss.dao;

import priv.jesse.netctoss.entity.Admin;

public interface AdminDAO {
	public Admin findByAdminCode(String code);
}
