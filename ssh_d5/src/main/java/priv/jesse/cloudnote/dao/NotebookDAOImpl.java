package priv.jesse.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.cloudnote.entity.Notebook;
@Repository("notebookDAO")
public class NotebookDAOImpl implements NotebookDAO {

	@Autowired
	private HibernateTemplate template;
	
	private static final long serialVersionUID = -4234777646561429450L;

	public NotebookDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Map<String, Object>> findNotebookByUserId(String userId) {
		//HQL
		//select new map(id,name) from Notebook where userId=?
		String hql = "select new map(id as id,name as name) from Notebook where userId=?";
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = template.find(hql, userId);
		return list;
	}

	public void saveNotebook(Notebook notebook) {
		template.save(notebook);
	}

	public Notebook findNotebookById(String id) {
		return template.get(Notebook.class, id);
	}

	public void updateNotebook(Notebook notebook) {
		template.update(notebook);
	}

}
