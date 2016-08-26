package priv.jesse.netctoss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Emp;

@Repository("empDAO")
public class EmpDAOSpringjdbcImpl implements EmpDAO {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate template;

	/*
	 * springjdbc 会将底层异常封装成RuntimeException抛出
	 */
	public void save(Emp emp) {
		String sql = "INSERT INTO employee_jesse VALUES(employee_seq_jesse.NEXTVAL,?,?)";
		Object[] params = new Object[] { emp.getName(), emp.getAge() };
		template.update(sql, params);
	}

	public List<Emp> findAll() {
		String sql = "SELECT * FROM employee_jesse";
		return template.query(sql, new EmpRowMapper());
	}

	/**
	 * 告诉JdbcTemplate如何将记录转换成对象
	 * 
	 * @author Jesse
	 */
	class EmpRowMapper implements RowMapper<Emp> {
		/*
		 * rowNum:正在被遍历的记录的下标（从0开始）
		 */
		public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
			Emp emp = new Emp();
			emp.setName(rs.getString("name"));
			emp.setAge(rs.getInt("age"));
			emp.setId(rs.getInt("id"));
			return emp;
		}

	}

//	public Emp findById(int id) {
//		String sql = "SELECT * FROM employee_jesse WHERE id=?";
//		Object[] params = new Object[]{id};
//		return template.queryForObject(sql,params, new EmpRowMapper());
//	}
	
	public Emp findById(int id){
		String sql = "SELECT * FROM employee_jesse WHERE id=?";
		Object[] params = new Object[]{id};
		List<Emp> emps = template.query(sql, params, new EmpRowMapper());
		if(emps != null && !emps.isEmpty()){
			return emps.get(0);
		}
		return null;
		
	}

	public void delete(int id) {
		String sql = "DELETE FROM employee_jesse WHERE id=?";
		Object[] params = new Object[]{id};
		template.update(sql, params);

	}

	public void updata(Emp emp) {
		String sql = "UPDATE employee_jesse SET name=?,age=? WHERE id=?";
		Object[] params = new Object[]{emp.getName(),emp.getAge(),emp.getId()};
		template.update(sql, params);
	}

}
