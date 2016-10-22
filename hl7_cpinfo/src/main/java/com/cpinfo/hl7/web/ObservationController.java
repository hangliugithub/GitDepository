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

import com.cpinfo.hl7.service.ObservationService;
import com.cpinfo.hl7.util.MyParser;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.resource.Observation;

@Controller
@RequestMapping("/Observation")
public class ObservationController {
	@Autowired
	private ObservationService observationService;
	
	//创建一个Observation
	@RequestMapping( method =RequestMethod.POST)
	public void creatRes(HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			Observation obs = observationService.createObservation(MyParser.parseToObject(str, contentType, Observation.class));
			
			res.setStatus(201);
			res.addHeader("Location", req.getRequestURL()+"/"+obs.getId().getValue());
			res.addHeader("Last-Modified",obs.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
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
			System.out.println(contentType);
			Observation obs = observationService.readObservation(id);
			String result = "";
			if("application/json".equalsIgnoreCase(contentType)){
				result = MyParser.parseToJSON(obs);
			}if("application/xml".equalsIgnoreCase(contentType)){
				result = MyParser.parseToXML(obs);
			}
			//System.out.println(result);
			res.setCharacterEncoding("utf-8");
			res.addHeader("Last-Modified",obs.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
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
			Observation obs = observationService.updateObservation(MyParser.parseToObject(str, contentType, Observation.class));
			
			res.addHeader("Location", req.getRequestURL()+"");
			res.addHeader("Last-Modified",obs.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
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
			int num = observationService.delObservation(id);
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
			List<Observation> list = observationService.findHisObservation(id);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Observation p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Observation p:list){
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
	
	//根据姓名搜索资源
	@RequestMapping(method=RequestMethod.GET)
	public void findRes(HttpServletRequest req,HttpServletResponse res,String name){
		try {
			String contentType = req.getContentType();
			List<Observation> list = observationService.findByName(name);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Observation p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Observation p:list){
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
