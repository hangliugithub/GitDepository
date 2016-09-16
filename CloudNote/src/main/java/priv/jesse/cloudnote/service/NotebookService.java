package priv.jesse.cloudnote.service;

import java.util.List;
import java.util.Map;

import priv.jesse.cloudnote.entity.Note;

public interface NotebookService {
	List<Map<String,Object>> listNotebooks(String userId);
	
	List<Map<String,Object>> listNotes(String notebookId);
	
	Note loadNote(String noteId);
	/**
	 * 更新笔记本的内容
	 * @param noteId 笔记Id
	 * @param title 笔记标题
	 * @param body 笔记内容
	 * @return
	 */
	Note updateNoteBody(String noteId,String title,String body);
	
	Note addNote(String title,String notebookId,String userId);
}
