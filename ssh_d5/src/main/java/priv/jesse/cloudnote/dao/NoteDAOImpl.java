package priv.jesse.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.cloudnote.entity.Note;
@Repository("noteDAO")
public class NoteDAOImpl implements NoteDAO {
	private static final long serialVersionUID = -6747944972909154748L;

	@Autowired
	private HibernateTemplate template;
	
	public NoteDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Map<String, Object>> findNoteByNotebookId(String notebookId) {
		//HQL
		String hql = "select new map(id as id,title as title) from Note where notebookId=?";
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = template.find(hql, notebookId);
		return list;
	}

	public Note findNoteById(String id) {
		return template.get(Note.class, id);
	}

	public void updateNote(Note note) {
		template.update(note);
	}

	public void saveNote(Note note) {
		template.save(note);

	}

	public void removeNote(Note note) {
		template.delete(note);
	}

}
