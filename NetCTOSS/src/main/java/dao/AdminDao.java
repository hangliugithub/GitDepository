package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import entity.Admin;
import utils.DBUtil;

public class AdminDao {
	/**
	 * Alt+shit+J
	 * 
	 * 根据账号查询管理员
	 * @param code 账号
	 * @return 管理员对象
	 * @throws SQLException
	 */
	public Admin findByCode(String code){
		String sql = "SELECT * FROM admin_info_jesse WHERE admin_code=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Admin a = new Admin();
				a.setAdminId(rs.getInt(1));
				a.setAdminCode(rs.getString(2));
				a.setPassword(rs.getString(3));
				a.setName(rs.getString(4));
				a.setTelephone(rs.getString(5));
				a.setEmail(rs.getString(6));
				a.setEnrolldate(rs.getTimestamp(7));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				DBUtil.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Test
	public void testFindById(){
		Admin a =  findByCode("caocao");
		System.out.println(a);
	}
}
