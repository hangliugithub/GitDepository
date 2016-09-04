package dao;

import java.util.List;

import entity.Emp;

public interface EmpMapper {
	public List<Emp> findAll();
	
	public Emp findById(int id);
	
	public void save(Emp emp);
	
	public void update(Emp emp);
	
	public void delete(int id);
}
