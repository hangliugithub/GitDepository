package test;

import java.util.ArrayList;
import java.util.List;

import bean.Stock;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestCase {
	/**
	 * java对象转json字符串
	 */
	public static void test1(){
		Stock s = new Stock();
		s.setCode("6000015");
		s.setName("微软");
		s.setPrice(16);
		JSONObject jsonObj = JSONObject.fromObject(s);
		System.out.println(jsonObj);
	}
	/**
	 * java对象数字或集合转换为json字符串
	 */
	public static void test2(){
		List<Stock> stocks = new ArrayList<Stock>();
		for(int i=0;i<5;i++){
			Stock s = new Stock();
			s.setCode("600001"+i);
			s.setName("微软"+i);
			s.setPrice(16*i/6);
			stocks.add(s);
		}
		
		JSONArray jsonArr = JSONArray.fromObject(stocks);
		System.out.println(jsonArr);
	}
	
	
	public static void main(String[] args){
		test1();
		
		test2();
	}
}
