package test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.jesse.cloudnote.dao.NotebookDAO;

public class NotebookDAOTest {
	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");

	}

	@Test
	public void testFindNotebookByUserId() {
		NotebookDAO dao = (NotebookDAO) ac.getBean("notebookDAO");
		List<Map<String, Object>> list = dao.findNotebookByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

}
