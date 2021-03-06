package com.cpinfo.hl7.dao;

import java.util.List;
import java.util.Map;

public interface DiagnosticReportDao {
	void creat(Map<String, String> diagMap);

	Map<String, String> findById(String id);

	//code
	List<Map<String, String>> findByName(String name);

	List<Map<String, String>> findHisById(String id);

	int delById(String id);

	void setActive(String id);

	void update(Map<String, String> diagMap);
}
