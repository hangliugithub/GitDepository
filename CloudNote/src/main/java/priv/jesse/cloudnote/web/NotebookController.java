package priv.jesse.cloudnote.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.jesse.cloudnote.entity.Note;
import priv.jesse.cloudnote.entity.Notebook;
import priv.jesse.cloudnote.service.NotebookService;
@RequestMapping("/notebook")
@Controller
public class NotebookController {

	@Autowired
	private NotebookService notebookService;
	
	public NotebookController() {
		
	}
	@RequestMapping("/listNotebooks.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> list(String userId){
		try{
			//System.out.println(userId);
			List<Map<String,Object>> list = notebookService.listNotebooks(userId);
			return new JsonResult<List<Map<String,Object>>>(list);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<List<Map<String, Object>>>(e.getMessage());
		}
	}
	
	@RequestMapping("/addNotebook.do")
	@ResponseBody
	public JsonResult<Notebook> addNotebook(String notebookName,String userId){
		try {
			Notebook notebook = notebookService.addNotebook(notebookName, userId);
			return new JsonResult<Notebook>(notebook);
		} catch (Exception e) {
			return new JsonResult<Notebook>(e.getMessage());
		}
	}
	
	@RequestMapping("/note/listNotes.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> listNotes(String notebookId){
		try {
			List<Map<String,Object>> list = notebookService.listNotes(notebookId);
			return new JsonResult<List<Map<String,Object>>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Map<String, Object>>>(e.getMessage());
		}
	}
	
	@RequestMapping("/note/loadNote.do")
	@ResponseBody
	public JsonResult<Note> showNote(String noteId){
		try {
			Note note = notebookService.loadNote(noteId);
			return new JsonResult<Note>(note);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Note>(e.getMessage());
		}
	}
	
	@RequestMapping("/note/saveNote.do")
	@ResponseBody
	public JsonResult<Note> saveNote(String noteId,String title,String body){
		try {
			Note note = notebookService.updateNoteBody(noteId, title, body);
			return new JsonResult<Note>(note);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Note>(e.getMessage());
		}
	}
	
	@RequestMapping("/note/addNote.do")
	@ResponseBody
	public JsonResult<Note> addNote(String title, String notebookId,String userId){
		try {
			Note note = notebookService.addNote(title, notebookId, userId);
			return new JsonResult<Note>(note);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Note>(e.getMessage());
		}
	}
	
	@RequestMapping("/note/deleteNote.do")
	@ResponseBody
	public JsonResult<Note> deleteNote(String noteId){
		try {
			Note note = notebookService.deleteNote(noteId);
			return new JsonResult<Note>(note);
		} catch (Exception e) {
			return new JsonResult<Note>(e.getMessage());
		}
	}
	
	
	@RequestMapping("/note/moveNote.do")
	@ResponseBody
	public JsonResult<Note> moveNote(String noteId,String toNotebookId){
		try {
			Note note = notebookService.moveNote(noteId, toNotebookId);
			return new JsonResult<Note>(note);
		} catch (Exception e) {
			return new JsonResult<Note>(e.getMessage());
		}
	}
	
}



