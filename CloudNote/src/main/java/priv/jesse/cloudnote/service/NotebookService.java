package priv.jesse.cloudnote.service;

import java.util.List;
import java.util.Map;

import priv.jesse.cloudnote.entity.Note;

public interface NotebookService {
	List<Map<String,Object>> listNotebooks(String userId);
	
	List<Map<String,Object>> listNotes(String notebookId);
	
	Note loadNote(String noteId);
	/**
	 * ���±ʼǱ�������
	 * @param noteId �ʼ�Id
	 * @param title �ʼǱ���
	 * @param body �ʼ�����
	 * @return
	 */
	Note updateNoteBody(String noteId,String title,String body);
	
	Note addNote(String title,String notebookId,String userId);
}
