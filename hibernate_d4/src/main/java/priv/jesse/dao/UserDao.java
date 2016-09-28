package priv.jesse.dao;

import priv.jesse.entity.User;

public interface UserDao {
	User findUserById(String id);
}
