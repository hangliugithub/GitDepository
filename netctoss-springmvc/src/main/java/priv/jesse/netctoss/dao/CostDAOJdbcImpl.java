package priv.jesse.netctoss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import priv.jesse.netctoss.entity.Cost;

//@Repository("costDAO")
public class CostDAOJdbcImpl implements CostDAO {

//	@Resource(name="dataSource")
	private DataSource ds;
	
	public List<Cost> findAll() {
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cost_jesse order by cost_id";
		List<Cost> list = new ArrayList<Cost>();
		try {
			conn= ds.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			while(rs.next()){
				list.add(createCost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 使用快捷键Alt+shift+M快捷把一段代码封装成的方法
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Cost createCost(ResultSet rs) throws SQLException {
		Cost cost = new Cost();
		cost.setCostId(rs.getInt(1));
		cost.setName(rs.getString(2));
		cost.setBaseDuration(rs.getInt(3));
		cost.setBaseCost(rs.getDouble(4));
		cost.setUnitCost(rs.getDouble(5));
		cost.setStatus(rs.getString(6));
		cost.setDescr(rs.getString(7));
		cost.setCreatime(rs.getTimestamp(8));
		cost.setStartime(rs.getTimestamp(9));
		cost.setCostType(rs.getString(10));
		return cost;
	}

	public Cost findById(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from cost_jesse where cost_id=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, new Integer(id));
			rs = ps.executeQuery();
			if(rs.next()){
				return createCost(rs);
			}else{
				throw new RuntimeException("没有找到！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
