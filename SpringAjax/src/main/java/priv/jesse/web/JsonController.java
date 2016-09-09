package priv.jesse.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.jesse.po.Person;

@Controller
@RequestMapping("/demo")
public class JsonController {
	/**
	 * ����String����
	 * @return
	 */
	@RequestMapping("/hello.do")
	
	//����spring��ע�⣬�����ǽ��������ķ��ؽ����ʽ��ΪJSON�ַ�������װ��httpЭ�����Ӧbody��
	@ResponseBody
	public Object execute(){
		return "Hello world!";
	}
	
	/**
	 * ����pojo
	 * @return
	 */
	@RequestMapping("/getPerson.do")
	@ResponseBody
	public Person getPerson(){
		Person p = new Person();
		p.setName("Catalina");
		p.setAge(22);
		p.setGender('F');
		List<String> list = new ArrayList<String>();
		list.add("����");
		p.setInterests(list);
		return p;
	}
	
	@RequestMapping("/getMap.do")
	@ResponseBody
	public Map<String,String> getMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", "tom");
		map.put("age", "22");
		return map;
	}
	
	@RequestMapping("/getList.do")
	@ResponseBody
	public List<Person> getList(){
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Tom",22,'F'));
		list.add(new Person("Jeary",23,'M'));
		return list;
	}
}










