package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entity.Cost;
import utils.DBUtil;

public class CostDao {
	
	/**
	 * ��ѯ���ݿ���cost������м�¼
	 * @return ����costʵ����List����
	 */
	public List<Cost> findAll(){
		Connection conn = null;
		try {
			conn  = DBUtil.getConnection();
			String sql = "select * from cost_jesse order by cost_id";
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()){
				Cost cost = createCost(rs);
				list.add(cost);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�ʷ�ʧ��");
		} finally{
			try {
				DBUtil.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ʹ�ÿ�ݼ�Alt+shift+M��ݰ�һ�δ����װ�ɵķ���
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
	@Test
	public void testFindAll(){
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		//System.out.println(list);
		for(Cost c:list){
			System.out.println(c);
		}
	}
	
	public Cost findById(String id){
		Connection conn = null;
		String sql = "SELECT "
				+ "cost_id,name,base_duration,base_cost,unit_cost,status,descr,creatime,startime,cost_type "
				+ "FROM cost_jesse WHERE cost_id=?";
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, new Integer(id));
			ResultSet rs = ps.executeQuery();
			Cost c = new Cost();
			while(rs.next()){
				c=createCost(rs);
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("û��������¼��");
		} finally {
			try {
				DBUtil.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ָ����Costʵ���������ݿ��е�cost��
	 * @param c
	 */
	public void saveCost(Cost c){
		System.out.println(c);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO cost_jesse VALUES ("
					+ "cost_seq_jesse.NEXTVAL,?,?,?,?,'1',?,SYSDATE,null,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			/*
			 * ��ʵ��ҵ���������������ֶο���Ϊnull������setInt,setDouble��������null����ʱ��
			 * ���ǵ���Object������
			 */
//			ps.setInt(2, c.getBaseDuration());
//			ps.setDouble(3, c.getBaseCost());
//			ps.setDouble(4, c.getUnitCost());
			ps.setObject(2, c.getBaseDuration());
			ps.setObject(3, c.getBaseCost());
			ps.setObject(4, c.getUnitCost());
			
			ps.setString(5, c.getDescr());
			ps.setString(6,c.getCostType());
			if(ps.executeUpdate()>0){
				//�ύ����
				ps.execute("commit");
				System.out.println("���Cost�ɹ���");
			}else{
				throw new RuntimeException("����ʷ��ײ�ʧ��!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.createStatement().execute("rollback");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("����ʷ��ײ�ʧ��!");
		} finally{
			try {
				conn.setAutoCommit(true);
				DBUtil.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void TestSaveCost(){
		Cost c = new Cost();
		c.setName("����ʱ��");
		c.setBaseCost(5.5);
		c.setBaseDuration(10);
		c.setUnitCost(5.6);
		new CostDao().saveCost(c);
	}
	
	/**
	 * ͨ��ָ����costId�����Ӧ��Cost��¼�����ݿ��cost����ɾ��
	 * @param id
	 */
	public boolean delCostById(String id){
		String sql = "DELETE FROM cost_jesse WHERE cost_id=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			if(ps.executeUpdate()>0){
				System.out.println("ɾ���ɹ���");
				ps.execute("commit");
				return true;
			}
		} catch (SQLException e) {
			try {
				conn.createStatement().execute("rollback");
				throw new RuntimeException("ɾ��ʧ�ܣ�");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void modifyCost(Cost c){
		String sql = "UPDATE cost_jesse SET "
				+ "name=?,cost_type=?,base_duration=?,base_cost=?,unit_cost=?,descr=? "
				+ "WHERE cost_id=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getCostType());
			
//			ps.setInt(3, c.getBaseDuration());
//			ps.setDouble(4, c.getBaseCost());
//			ps.setDouble(5, c.getUnitCost());
			
			ps.setObject(3, c.getBaseDuration());
			ps.setObject(4, c.getBaseCost());
			ps.setObject(5, c.getUnitCost());
			
			ps.setString(6, c.getDescr());
			ps.setInt(7, c.getCostId());
			if(ps.executeUpdate()>0){
				conn.commit();
				System.out.println("�޸ĳɹ���");
			}else{
				throw new RuntimeException("�޸�ʧ�ܣ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("�޸�ʧ�ܣ�");
			}
		} finally{
			try {
				conn.setAutoCommit(true);
				DBUtil.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}


















