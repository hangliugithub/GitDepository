package dao;

import java.util.List;
import java.util.Map;

import annotation.MyBatisRepository;
import entity.Emp;
@MyBatisRepository
public interface EmpMapper {
	public List<Emp> findAll();

	public Emp findById(Integer id);

	public void save(Emp emp);

	public void update(Emp emp);

	public void delete(Integer id);

	public List<Map<String, Object>> findById2(Integer id);
}
