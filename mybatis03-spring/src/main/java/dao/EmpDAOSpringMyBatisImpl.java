package dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import entity.Emp;
//@Repository("empDAO")
public class EmpDAOSpringMyBatisImpl implements EmpDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	public List<Emp> findAll() {
		// TODO Auto-generated method stub
		return template.selectList("findAll");
	}

	public Emp findById(int id) {
		return template.selectOne("findById", id);
	}

	public void save(Emp emp) {
			int n = template.insert("save", emp);
			if(n>0){
				System.out.println("����ɹ���");
			}else{
				throw new RuntimeException("����ʧ�ܣ�");
			}
		
	}

	public void update(Emp emp) {
			int n = template.update("update", emp);
			if(n>0){
				System.out.println("���³ɹ���");
			}else{
				throw new RuntimeException("����ʧ�ܣ�");
			}
	}

	public void delete(int id) {
			int n = template.delete("delete", id);
			if(n>0){
				System.out.println("ɾ���ɹ���");
				template.commit();
			}else{
				throw new RuntimeException("ɾ��ʧ�ܣ�");
			}
	}
	
}
