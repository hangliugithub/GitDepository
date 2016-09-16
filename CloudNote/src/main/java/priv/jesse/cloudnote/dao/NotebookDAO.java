package priv.jesse.cloudnote.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface NotebookDAO extends Serializable{
	List<Map<String,Object>> findNotebookByUserId(String userId);

}
