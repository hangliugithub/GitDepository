package priv.jesse.netctoss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Admin;

//�־ò�
//@Repository("adminDAO")
public class AdminDAOJdbcImpl implements AdminDAO {
	@Resource(name = "datasource")
	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public Admin findByAdminCode(String code) {
		Admin admin = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rst = null;

		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM admin_info_jesse WHERE admin_code=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, code);
			rst = stat.executeQuery();
			if (rst.next()) {
				admin = new Admin();
				admin.setAdminId(rst.getInt(1));
				admin.setAdminCode(rst.getString(2));
				admin.setPassword(rst.getString(3));
				admin.setName(rst.getString(4));
				admin.setTelephone(rst.getString(5));
				admin.setEmail(rst.getString(6));
				admin.setEnrolldate(rst.getTimestamp(7));
			}

		} catch (SQLException e) {
			// step1:��¼��־
			e.printStackTrace();
			/*
			 * step2:���쳣�Ƿ��ܹ��ָ� ������ܹ��ָ���������ϵͳ�쳣���磺���ݿ����ֹͣ������ʾ�û��Ժ����ԡ� ����ܹ��ָ����������ָ�
			 */
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return admin;
	}

}
