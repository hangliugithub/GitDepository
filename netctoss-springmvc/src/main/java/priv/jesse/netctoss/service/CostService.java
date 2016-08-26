package priv.jesse.netctoss.service;

import java.util.List;

import priv.jesse.netctoss.entity.Cost;

public interface CostService {
	public List<Cost> listAll();
	public Cost detialCost(String id);
}
