package com.cpinfo.hl7.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cpinfo.hl7.service.ConditionService;
import com.cpinfo.hl7.util.MyParser;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.resource.Condition;

@Controller
@RequestMapping("/Condition")
public class ConditionController {
	
	@Autowired
	private ConditionService conditionService;
	
	//创建资源
	@RequestMapping( method =RequestMethod.POST)
	public void creatRes(HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			Condition c = conditionService.createCondition(MyParser.parseToObject(str, contentType, Condition.class));
			
			res.setStatus(201);
			res.addHeader("Location", req.getRequestURL()+"/"+c.getId().getValue());
			res.addHeader("Last-Modified",c.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//根据id读取资源
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(id);
			System.out.println(contentType);
			Condition c = conditionService.readCondition(id);
			String result = "";
			if("application/json".equalsIgnoreCase(contentType)){
				result = MyParser.parseToJSON(c);
			}if("application/xml".equalsIgnoreCase(contentType)){
				result = MyParser.parseToXML(c);
			}
			//System.out.println(result);
			res.setCharacterEncoding("utf-8");
			res.addHeader("Last-Modified",c.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			//res.setStatus(200);
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
	}
	
	
	//更新资源
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public void updRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			Condition c = conditionService.updateCondition(MyParser.parseToObject(str, contentType, Condition.class));
			
			res.setStatus(201);
			res.addHeader("Location", req.getRequestURL()+"");
			res.addHeader("Last-Modified",c.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//删除资源
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			System.out.println(id);
			int num = conditionService.delCondition(id);
			if(num<=0){
				res.setStatus(404);
			}
			res.setStatus(410);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取历史版本
	@RequestMapping(value="/{id}/_history",method=RequestMethod.GET)
	public void getResHistory(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			List<Condition> list = conditionService.findHisCondition(id);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Condition p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Condition p:list){
					result += MyParser.parseToJSON(p)+",";
				}
			}
			
			res.setCharacterEncoding("utf-8");
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//诊断名称搜索资源
	@RequestMapping(method=RequestMethod.GET)
	public void findResByPatient(HttpServletRequest req,HttpServletResponse res,String conditionName){
		try {
			String contentType = req.getContentType();
			List<Condition> list = conditionService.findByName(conditionName);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Condition p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Condition p:list){
					result += MyParser.parseToJSON(p)+",";
				}
			}
			
			res.setCharacterEncoding("utf-8");
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	
	public static String readRequestBody(HttpServletRequest req) throws IOException{
		req.setCharacterEncoding("utf-8");
		String str = "";
		BufferedReader reader = req.getReader();
		String tmp;
		while((tmp=reader.readLine())!=null){
			str+=tmp;
		}
		reader.close();
		return str;
	}
	
}
