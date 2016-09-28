package test;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.entity.Note;
import priv.jesse.cloudnote.service.NotebookService;

public class NotebookServiceTest {

	private ApplicationContext ac;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
	}
	
	@Test
	public void testListNotebooks() {
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		List<Map<String,Object>> list = service.listNotebooks("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Map<String,Object> map: list){
			System.out.println(map);
		}
	}

	@Test
	public void testListNotes() {
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		List<Map<String,Object>> list = service.listNotes("0b11444a-a6d6-45ff-8d46-282afaa6a655");
		for(Map<String,Object> map: list){
			System.out.println(map);
		}
	}

	@Test
	public void testLoadNote() {
		NotebookService service = (NotebookService) ac.getBean("notebookService");
		Note note = service.loadNote("74ac4c08-ae60-4632-9af5-3a917a1f4a7b");
		System.out.println(note);
	}

	@Test
	public void testUpdateNoteBody() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNote() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteNote() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNotebook() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveNote() {
		fail("Not yet implemented");
	}

}
