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
				System.out.println("����ɹ���");
			}else{
				throw new RuntimeException("����ʧ�ܣ�");
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
				System.out.println("���³ɹ���");
			}else{
				throw new RuntimeException("����ʧ�ܣ�");
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
				System.out.println("ɾ��idΪ"+id+"��Ա���ɹ���");
			}else{
				throw new RuntimeException("idΪ"+id+"��Ա�������ڣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		} finally {
			MybatisUtil.close(session);
		}
	}
}


