package priv.jesse.netctoss.dao;

import java.util.List;

import priv.jesse.netctoss.entity.Cost;

public interface CostDAO {
	public List<Cost> findAll();
	public Cost findById(String id);
}
