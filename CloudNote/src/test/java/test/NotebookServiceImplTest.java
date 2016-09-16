package test;


import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.entity.Note;
import priv.jesse.cloudnote.service.NotebookService;

public class NotebookServiceImplTest {
	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");
	}
	@Test
	public void testListNotebooks() {
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		List<Map<String,Object>> list = service.listNotebooks("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Map<String,Object> map : list){
			System.out.println(map);
		}
	}

	@Test
	public void testListNotes(){
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		List<Map<String,Object>> list = service.listNotes("0cd94778-4d52-486d-a35d-263b3cfe6de9");
		for(Map<String,Object> map : list){
			System.out.println(map);
		}
	}
	
	@Test
	public void testUpdateNoteBody(){
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		//Note note = service.loadNote("01da5d69-89d5-4140-9585-b559a97f9cb0");
		String noteId = "01da5d69-89d5-4140-9585-b559a97f9cb0";
		String title = "测试更新笔记内容";
		String body = "asdfgjklkdls";
		Note note = service.updateNoteBody(noteId, title, body);
		System.out.println(note);
	}
	
}













