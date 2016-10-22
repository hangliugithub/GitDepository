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

import com.cpinfo.hl7.service.DiagnosticReportService;
import com.cpinfo.hl7.util.MyParser;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;

@Controller
@RequestMapping("/DiagnosticReport")
public class DiagnosticReportController {
	
	@Autowired
	private DiagnosticReportService diagService;
	
	@RequestMapping( method =RequestMethod.POST)
	public void creatRes(HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			DiagnosticReport diag = diagService.createDiag(MyParser.parseToObject(str, contentType, DiagnosticReport.class));
			
			res.setStatus(201);
			res.addHeader("Location", req.getRequestURL()+"/"+diag.getId().getValue());
			res.addHeader("Last-Modified",diag.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			DiagnosticReport diag = diagService.readDiag(id);
			String result = "";
			if("application/json".equalsIgnoreCase(contentType)){
				result = MyParser.parseToJSON(diag);
			}if("application/xml".equalsIgnoreCase(contentType)){
				result = MyParser.parseToXML(diag);
			}
			//System.out.println(result);
			res.setCharacterEncoding("utf-8");
			res.addHeader("Last-Modified",diag.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			//res.setStatus(200);
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public void updRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			DiagnosticReport diag = diagService.updateDiagnosticReport(MyParser.parseToObject(str, contentType, DiagnosticReport.class));
			
			res.addHeader("Location", req.getRequestURL()+"");
			res.addHeader("Last-Modified",diag.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			System.out.println(id);
			int num = diagService.delDiag(id);
			if(num<=0){
				res.setStatus(404);
			}
			res.setStatus(410);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}/_history",method=RequestMethod.GET)
	public void getResHistory(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			List<DiagnosticReport> list = diagService.findHisObservation(id);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(DiagnosticReport p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(DiagnosticReport p:list){
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
	
	@RequestMapping(method=RequestMethod.GET)
	public void findRes(HttpServletRequest req,HttpServletResponse res,String name){
		try {
			String contentType = req.getContentType();
			List<DiagnosticReport> list = diagService.findByName(name);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(DiagnosticReport p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(DiagnosticReport p:list){
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
