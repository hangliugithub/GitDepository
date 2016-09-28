package priv.jesse.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.jesse.cloudnote.dao.NoteDAO;
import priv.jesse.cloudnote.dao.NotebookDAO;
import priv.jesse.cloudnote.entity.Note;
import priv.jesse.cloudnote.entity.Notebook;
@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
	
	@Autowired
	private NotebookDAO notebookDAO;
	
	@Autowired
	private NoteDAO noteDAO;
	
	public NotebookServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotebooks(String userId) {
		if(userId==null || userId.trim().isEmpty()){
			throw new ServiceException("userId不能为空！");
		}
		return notebookDAO.findNotebookByUserId(userId);
	}

	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotes(String notebookId) {
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new ServiceException("notebookId不能为空！");
		}
		return noteDAO.findNoteByNotebookId(notebookId);
	}

	@Transactional(readOnly=true)
	public Note loadNote(String noteId) {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new ServiceException("noteId不能为空");
		}
		return noteDAO.findNoteById(noteId);
	}

	@Transactional
	public Note updateNoteBody(String noteId, String title, String body) {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new ServiceException("noteId不能为空");
		}
		if(title==null){
			title = "";
		}
		if(body==null){
			body = "";
		}
		Note note = noteDAO.findNoteById(noteId);
		if(!title.trim().isEmpty()){
			note.setTitle(title);
		}
		note.setBody(body);
		note.setLastModifyTime(System.currentTimeMillis());
		noteDAO.updateNote(note);
		return note;
	}

	@Transactional
	public Note addNote(String title, String notebookId,String userId) {
		if(title==null || title.trim().isEmpty()){
			throw new ServiceException("标题不能为空");
		}
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new ServiceException("notebookId不能为空");
		}
		//实例化note
		Note note = new Note();
		note.setId(UUID.randomUUID().toString());
		note.setNotebookId(notebookId);
		note.setUserId(userId);
		note.setTitle(title);
		note.setStatusId(Note.NORMAL_STATUS);
		note.setTypeId(Note.NORMAL_TYPE);
		note.setBody("");
		long time = System.currentTimeMillis();
		note.setCreateTime(time);
		note.setLastModifyTime(time);
		noteDAO.saveNote(note);
		//调用DAO保存note
		return note;
	}

	@Transactional
	public Note deleteNote(String noteId) {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new ServiceException("noteId不能为空！");
		}
		Note note = noteDAO.findNoteById(noteId);
		if(note==null){
			throw new ServiceException("ID不存在！");
		}
		if(Note.NORMAL_TYPE.equals(note.getTypeId())){
			note.setTypeId(Note.DELETE_TYPE);
			note.setLastModifyTime(System.currentTimeMillis());
			noteDAO.updateNote(note);
			return note;
		}
		throw new ServiceException("只能删除正常笔记！");
	}

	@Transactional
	public Notebook addNotebook(String notebookName, String userId) {
		if(notebookName==null || notebookName.trim().isEmpty()){
			throw new ServiceException("笔记本名字不能为空！");
		}
		if(userId == null || userId.trim().isEmpty()){
			throw new ServiceException("notebookId不能为空");
		}
		Notebook notebook = new Notebook();
		notebook.setId(UUID.randomUUID().toString());
		notebook.setName(notebookName);
		notebook.setUserId(userId);
		notebook.setTypeId("1");
		notebook.setCreateTime(new Timestamp(System.currentTimeMillis()));
		notebookDAO.saveNotebook(notebook);
		return notebook;
	}

	@Transactional
	public Note moveNote(String noteId, String toNotebookId) {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new ServiceException("noteId不能为空！");
		}
		if(toNotebookId == null || toNotebookId.trim().isEmpty()){
			throw new ServiceException("notebookId不能为空");
		}
		Note note = noteDAO.findNoteById(noteId);
		if(toNotebookId.equals(note.getNotebookId())){
			
		}
		
		note.setNotebookId(toNotebookId);
		note.setLastModifyTime(System.currentTimeMillis());
		noteDAO.updateNote(note);
		return note;
	}

}
