package com.cpinfo.hl7.dao;

import java.util.List;
import java.util.Map;

public interface PatientDao {
	
	void creat(Map<String,String> patient);
	
	Map<String,String> findById(String id);
	
	List<Map<String,String>> findByName(String name);
	
	List<Map<String,String>> findHisById(String id);
	
	
	int delById(String id);
	
	void setActive(String id);
	void update(Map<String,String> patient);
	
	
}
