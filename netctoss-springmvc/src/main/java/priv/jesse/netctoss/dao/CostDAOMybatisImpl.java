package priv.jesse.netctoss.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Cost;
@Repository("costDAO")
public class CostDAOMybatisImpl implements CostDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Cost> findAll() {
		return sqlSessionTemplate.selectList("priv.jesse.netctoss.dao.CostMapper.findAll");
	}

	public Cost findById(String id) {
		return sqlSessionTemplate.selectOne("priv.jesse.netctoss.dao.CostMapper.findById", new Integer(id));
	}

}
