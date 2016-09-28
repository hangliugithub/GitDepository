package priv.jesse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(User.class, id);
	}

}
