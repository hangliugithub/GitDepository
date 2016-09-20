package priv.jesse.cloudnote.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import priv.jesse.cloudnote.entity.Notebook;

public interface NotebookDAO extends Serializable{
	List<Map<String,Object>> findNotebookByUserId(String userId);
	
	void saveNotebook(Notebook notebook);

	Notebook findNotebookById(String id);
	
	void updateNotebook(Notebook notebook);
}
