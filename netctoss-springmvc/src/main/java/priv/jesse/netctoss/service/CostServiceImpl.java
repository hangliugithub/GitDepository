package priv.jesse.netctoss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.jesse.netctoss.dao.CostDAO;
import priv.jesse.netctoss.entity.Cost;
@Service("costService")
public class CostServiceImpl implements CostService{
	@Resource(name="costDAO")
	private CostDAO dao;
	
	public List<Cost> listAll() {
		try{
			List<Cost> list = dao.findAll();
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Cost detialCost(String id) {
		try{
			Cost cost = dao.findById(id);
			return cost;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
