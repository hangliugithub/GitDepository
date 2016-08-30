package priv.jesse.netctoss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Admin;
@Repository("adminDAO")
public class AdminDAOSpringjdbcImpl implements AdminDAO {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate template;
	
	public Admin findByAdminCode(String code) {
		String sql = "SELECT * FROM admin_info WHERE admin_code=?";
		Object[] params = new Object[]{code};
		List<Admin> admin = template.query(sql, params, new AdminRowMapper());
		if(admin!=null && !admin.isEmpty()){
			return admin.get(0);
		}
		return null;
	}
	
	class AdminRowMapper implements RowMapper<Admin>{

		public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			Admin admin = new Admin();
			admin.setAdminId(rs.getInt(1));
			admin.setAdminCode(rs.getString(2));
			admin.setPassword(rs.getString(3));
			admin.setName(rs.getString(4));
			admin.setTelephone(rs.getString(5));
			admin.setEmail(rs.getString(6));
			admin.setEnrolldate(rs.getTimestamp(7));
			return admin;
		}
		
	}

}
