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
				System.out.println("插入成功！");
			}else{
				throw new RuntimeException("插入失败！");
			}
		
	}

	public void update(Emp emp) {
			int n = template.update("update", emp);
			if(n>0){
				System.out.println("更新成功！");
			}else{
				throw new RuntimeException("更新失败！");
			}
	}

	public void delete(int id) {
			int n = template.delete("delete", id);
			if(n>0){
				System.out.println("删除成功！");
				template.commit();
			}else{
				throw new RuntimeException("删除失败！");
			}
	}
	
}
