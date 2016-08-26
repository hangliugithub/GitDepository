package priv.jesse.netctoss.dao;

import java.util.List;

import priv.jesse.netctoss.entity.Emp;

public interface EmpDAO {
	public void save(Emp emp);

	public List<Emp> findAll();

	public Emp findById(int id);

	public void delete(int id);

	public void updata(Emp emp);
}
