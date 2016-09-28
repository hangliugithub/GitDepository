package priv.jesse.cloudnote.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import priv.jesse.cloudnote.entity.Note;

public interface NoteDAO extends Serializable {
	List<Map<String, Object>> findNoteByNotebookId(String notebookId);
	
	Note findNoteById(String id);
	
	void updateNote(Note note);
	
	void saveNote(Note note);
	
	void removeNote(Note note);
}
