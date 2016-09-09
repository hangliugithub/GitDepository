package priv.jesse.cloudnote.dao;

import priv.jesse.cloudnote.entity.User;

public interface UserDAO {
	User findUserById(String id);
	void saveUser(User user);
}
