package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Emp;

public class EmpDao {
	public List<Emp> findAll(){
		
		List<Emp> result = new ArrayList<Emp>();
		//Ä£ÄâÊµÏÖ
//		for(int i=0;i<5;i++){
//			result.add(new Emp(i,"user"+i,"CLERK",3000.0*(i+1)));
//		}
		Connection conn = DBUtils.getConnection();
		try {
			Statement sta = conn.createStatement();
			String sql = "SELECT empno,ename,job,sal FROM emp_jesse";
			ResultSet res = sta.executeQuery(sql);
			while(res.next()){
				Emp e = new Emp();
				e.setEmpno(res.getInt(1));
				e.setEname(res.getString(2));
				e.setJob(res.getString(3));
				e.setSal(Double.parseDouble(res.getInt(4)+""));
				result.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.close(conn);
		}
		return result;
	}
	
	public boolean add(Emp e){
		Connection conn = DBUtils.getConnection();
		String sql = "INSERT INTO emp_jesse (empno,ename,job,sal) values (seq_emp_id_jesse.NEXTVAL,?,?,?)";
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			if(e==null){
				return false;
			}
			prep.setString(1, e.getEname());
			prep.setString(2,e.getJob());
			prep.setInt(3, e.getSal().intValue());
			int res = prep.executeUpdate();
			if(res>0){
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally{
			DBUtils.close(conn);
		}
		return false;
	}
}
