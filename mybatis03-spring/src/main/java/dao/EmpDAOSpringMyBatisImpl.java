package dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import entity.Emp;
@Repository("empDAO")
public class EmpDAOSpringMyBatisImpl implements EmpDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	public List<Emp> findAll() {
		// TODO Auto-generated method stub
		return template.selectList("findAll");
	}

	public Emp findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Emp emp) {
		// TODO Auto-generated method stub
		
	}

	public void update(Emp emp) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
