package test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.dao.NoteDAO;
import priv.jesse.cloudnote.entity.Note;

public class NoteDAOTest {
	
	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");

	}

	@Test
	public void testFindNoteByNotebookId(){
		NoteDAO dao = (NoteDAO) ac.getBean("noteDAO");
		List<Map<String,Object>> list = dao.findNoteByNotebookId("0cd94778-4d52-486d-a35d-263b3cfe6de9");
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testFindNoteById(){
		NoteDAO dao = (NoteDAO) ac.getBean("noteDAO");
		Note note = dao.findNoteById("054449b4-93d4-4f97-91cb-e0043fc4497f");
		System.out.println(note);
	}

}
