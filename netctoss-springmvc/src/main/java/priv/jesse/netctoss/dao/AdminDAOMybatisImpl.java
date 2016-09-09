package priv.jesse.netctoss.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Admin;
@Repository("adminDAO")
public class AdminDAOMybatisImpl implements AdminDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Admin findByAdminCode(String code) {
		return sqlSessionTemplate.selectOne("priv.jesse.netctoss.dao.AdminMapper.findByAdminCode", code);
	}

}
