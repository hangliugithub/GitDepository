package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import entity.Emp;
import utils.MybatisUtil;

public class EmpDAO {
	
	
	public List<Emp> findAll(){
		SqlSession session = MybatisUtil.getSqlSession();
		List<Emp> list = session.selectList("dao.EmpMapper.findAll");
		MybatisUtil.close(session);
		return list;
	}
	
	public Emp findById(int id){
		SqlSession session = MybatisUtil.getSqlSession();
		Emp emp = session.selectOne("dao.EmpMapper.findById", id);
		return emp;
	}
	
	public List<Map<String,Object>> findById2(int id){
		SqlSession session = MybatisUtil.getSqlSession();
		List<Map<String,Object>> list = session.selectList("dao.EmpMapper.findById2", id);
		return list;
	}
	
	public void save(Emp emp){
		SqlSession session = MybatisUtil.getSqlSession();
		try{
			int n = session.insert("dao.EmpMapper.save", emp);
			if(n>0){
				session.commit();
				System.out.println("保存成功！");
			}else{
				throw new RuntimeException("保存失败！");
			}
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		} finally{
			MybatisUtil.close(session);
		}
		
	}
	
	public void update(Emp emp){
		SqlSession session = MybatisUtil.getSqlSession();
		try{
			int n = session.update("dao.EmpMapper.update", emp);
			if(n>0){
				session.commit();
				System.out.println("更新成功！");
			}else{
				throw new RuntimeException("更新失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		} finally{
			MybatisUtil.close(session);
		}
	}
	
	public void delete(int id){
		SqlSession session = MybatisUtil.getSqlSession();
		try{
			int n = session.delete("dao.EmpMapper.delete", id);
			if(n>0){
				session.commit();
				System.out.println("删除id为"+id+"的员工成功！");
			}else{
				throw new RuntimeException("id为"+id+"的员工不存在！");
			}
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		} finally {
			MybatisUtil.close(session);
		}
	}
}


