package priv.jesse.cloudnote.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.cloudnote.entity.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private HibernateTemplate template;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public User findUserById(String id) {
		return template.get(User.class, id);
	}

	public void saveUser(User user) {
		template.save(user);
	}

	public User findUserByName(String name) {
		String hql = "from User where name=?";
		@SuppressWarnings("unchecked")
		List<User> list = template.find(hql, name);
		if(list==null||list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void updateUser(User user) {
		template.update(user);
	}

}
